package com.example.elasticsearch_assignment.service;

import com.example.elasticsearch_assignment.dto.response.CourseDocumentDto;

import java.time.OffsetDateTime;
import java.util.List;

public interface SearchService {

    List<CourseDocumentDto> searchDocument(
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
    );
}
