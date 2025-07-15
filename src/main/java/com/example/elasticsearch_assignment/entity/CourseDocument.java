package com.example.elasticsearch_assignment.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;
import org.springframework.data.elasticsearch.core.suggest.Completion;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Document(indexName = "courses")
public class CourseDocument {

    @Id
    private int id;

    @MultiField(
            mainField = @Field(type = FieldType.Text),
            otherFields = {
                    @InnerField(suffix = "keyword", type = FieldType.Keyword)
            }
    )
    private String title;

    @MultiField(
            mainField = @Field(type = FieldType.Text),
            otherFields = {
                    @InnerField(suffix = "keyword", type = FieldType.Keyword)
            }
    )
    private String description;

    @Field(type = FieldType.Keyword)
    private String category;

    @Field(type = FieldType.Keyword)
    private String type;

    @Field(type = FieldType.Text)
    private String gradeRange;

    @Field(type = FieldType.Integer)
    private int minAge;

    @Field(type = FieldType.Integer)
    private int maxAge;

    @Field(type = FieldType.Float)
    private float price;

    @Field(type = FieldType.Date, format = DateFormat.date_time)
    private OffsetDateTime nextSessionDate;

    @CompletionField(maxInputLength = 100)
    private Completion suggest;  // Part of assignment B

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGradeRange() {
        return gradeRange;
    }

    public void setGradeRange(String gradeRange) {
        this.gradeRange = gradeRange;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
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

    public Completion getSuggest() {
        return suggest;
    }

    public void setSuggest(Completion suggest) {
        this.suggest = suggest;
    }
}
