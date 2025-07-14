package com.example.elasticsearch_assignment.mapper;

import com.example.elasticsearch_assignment.dto.response.CourseDocumentDto;
import com.example.elasticsearch_assignment.entity.CourseDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseDocumentDto toCourseDocumentDto(CourseDocument courseDocument);
}
