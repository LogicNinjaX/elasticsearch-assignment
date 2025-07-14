package com.example.elasticsearch_assignment.dto.response;


import java.time.OffsetDateTime;

public class CourseDocumentDto {

    private int id;

    private String title;

    private String category;

    private float price;

    private OffsetDateTime nextSessionDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public OffsetDateTime getNextSessionDate() {
        return nextSessionDate;
    }

    public void setNextSessionDate(OffsetDateTime nextSessionDate) {
        this.nextSessionDate = nextSessionDate;
    }
}
