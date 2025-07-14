package com.example.elasticsearch_assignment.dto.response;

import java.util.List;

public class SearchResponseDto {

    private int total;

    private List<CourseDocumentDto> courses;

    public SearchResponseDto(int total, List<CourseDocumentDto> courses) {
        this.total = total;
        this.courses = courses;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<CourseDocumentDto> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseDocumentDto> courses) {
        this.courses = courses;
    }
}
