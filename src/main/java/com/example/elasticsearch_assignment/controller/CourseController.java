package com.example.elasticsearch_assignment.controller;

import com.example.elasticsearch_assignment.dto.response.CourseDocumentDto;
import com.example.elasticsearch_assignment.dto.response.SearchResponseDto;
import com.example.elasticsearch_assignment.service.SearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseController {

    private final SearchService service;

    public CourseController(SearchService service) {
        this.service = service;
    }


    @GetMapping("/search")
    public ResponseEntity<SearchResponseDto> searchCourses(
            @RequestParam String q,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Float minPrice,
            @RequestParam(required = false) Float maxPrice,
            @RequestParam(required = false) OffsetDateTime startDate,
            @RequestParam(required = false, defaultValue = "nextSessionDate") String sort,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    )
    {
        List<CourseDocumentDto> documentList = service.searchDocument(
                q,
                minAge,
                maxAge,
                minPrice,
                maxPrice,
                category,
                type,
                startDate,
                sort,
                page,
                size
        );

        return ResponseEntity.ok(new SearchResponseDto(documentList.size(), documentList));
    }


    @GetMapping("/search/suggest")
    public List<String> getS(@RequestParam String q){
        return service.getSuggestion(q);
    }
}
