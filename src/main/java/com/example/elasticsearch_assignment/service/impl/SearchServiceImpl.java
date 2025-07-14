package com.example.elasticsearch_assignment.service.impl;


import co.elastic.clients.elasticsearch._types.query_dsl.*;
import com.example.elasticsearch_assignment.dto.response.CourseDocumentDto;
import com.example.elasticsearch_assignment.entity.CourseDocument;
import com.example.elasticsearch_assignment.exception.InvalidRequestParameterException;
import com.example.elasticsearch_assignment.service.SearchService;
import com.example.elasticsearch_assignment.mapper.CourseMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class SearchServiceImpl implements SearchService {

    private final ElasticsearchOperations elasticsearchOperations;
    private final CourseMapper courseMapper;


    public SearchServiceImpl(ElasticsearchOperations elasticsearchOperations, CourseMapper courseMapper) {
        this.elasticsearchOperations = elasticsearchOperations;
        this.courseMapper = courseMapper;
    }

    @Override
    public List<CourseDocumentDto> searchDocument(
            String query,
            Integer minAge,
            Integer maxAge,
            Float minPrice,
            Float maxPrice,
            String category,
            String type,
            OffsetDateTime startDate,
            String sort,
            int page,
            int size

    ) {
        BoolQuery.Builder bool = QueryBuilders.bool();

        MultiMatchQuery matchQuery;

        if (query == null || query.trim().isEmpty()){
            throw new InvalidRequestParameterException("query must not be empty");
        }else {
            //Full-text search
            matchQuery = MultiMatchQuery.of(m ->
                    m.fields("title", "description").query(query).type(TextQueryType.BestFields));
            bool.must(m -> m.multiMatch(matchQuery));
        }


        //Exact filters
        TermQuery categoryFilter;
        if (Objects.nonNull(category)){
            categoryFilter = TermQuery.of(t ->
                    t.field("category")
                            .value(category)
            );

            bool.filter(f -> f.term(categoryFilter));
        }

        //Exact filters

        TermQuery typeFilter;
        if (Objects.nonNull(type)){
            typeFilter = TermQuery.of(t ->
                    t.field("type")
                            .value(type.toUpperCase())
            );

            bool.filter(f -> f.term(typeFilter));
        }


        //Range filters
        RangeQuery minAgeRange;
        if (Objects.nonNull(minAge)){
            minAgeRange = RangeQuery.of(rq ->
                    rq.number(nrq -> nrq.field("minAge").gte((double) minAge))
            );

            bool.filter(f -> f.range(minAgeRange));
        }

        //Range filters
        RangeQuery maxAgeRange;
        if (Objects.nonNull(maxAge)){
            maxAgeRange = RangeQuery.of(rq ->
                    rq.number(nrq -> nrq.field("maxAge").lte((double) maxAge))
            );

            bool.filter(f -> f.range(maxAgeRange));
        }

        //Range filters
        RangeQuery priceRange;
        if (Objects.nonNull(minPrice) && Objects.nonNull(maxPrice)){
            priceRange = RangeQuery.of(rq ->
                    rq.number(nrq -> nrq
                            .field("price")
                            .gte((double) minPrice)
                            .lte((double) maxPrice)));

            bool.filter(f -> f.range(priceRange));
        }



        //Date filter
        RangeQuery startDateRange;

        if (Objects.nonNull(startDate)){
            startDateRange = RangeQuery.of(fn ->
                    fn.date(dr -> dr.field("nextSessionDate").gte(startDate.toString()))
            );

            bool.filter(f -> f.range(startDateRange));
        }






        Sort sortField = switch (sort) {
            case "priceAsc" -> Sort.by(Sort.Order.asc("price"));
            case "priceDesc" -> Sort.by(Sort.Order.desc("price"));
            default -> Sort.by(Sort.Order.asc("nextSessionDate")); // Upcoming
        };



        Pageable pageable = PageRequest.of(page-1, size, sortField);

        NativeQuery nativeQuery = NativeQuery.builder()
                .withQuery(fn -> fn.bool(bool.build()))
                .withPageable(pageable)
                .build();

        SearchHits<CourseDocument> hits = elasticsearchOperations
                .search(nativeQuery, CourseDocument.class);

        return hits.getSearchHits()
                .stream().map(SearchHit::getContent)
                .map(courseMapper::toCourseDocumentDto) // mapping to dto
                .toList();

    }


}
