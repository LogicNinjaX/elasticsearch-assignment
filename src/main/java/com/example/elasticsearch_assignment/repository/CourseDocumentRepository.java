package com.example.elasticsearch_assignment.repository;

import com.example.elasticsearch_assignment.entity.CourseDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CourseDocumentRepository extends ElasticsearchRepository<CourseDocument, Integer> {
}
