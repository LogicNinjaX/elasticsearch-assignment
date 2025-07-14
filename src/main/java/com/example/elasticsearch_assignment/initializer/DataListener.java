package com.example.elasticsearch_assignment.initializer;

import com.example.elasticsearch_assignment.entity.CourseDocument;
import com.example.elasticsearch_assignment.repository.CourseDocumentRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

@Component
public class DataListener implements CommandLineRunner {

    private final CourseDocumentRepository courseDocumentRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(DataListener.class);

    public DataListener(CourseDocumentRepository courseDocumentRepository) {
        this.courseDocumentRepository = courseDocumentRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        TypeReference<List<CourseDocument>> typeReference = new TypeReference<>() {};

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sample-courses.json");
        if (inputStream == null){
            throw new FileNotFoundException("File not found");
        }

        List<CourseDocument> documentList = objectMapper.readValue(inputStream, typeReference);
        courseDocumentRepository.saveAll(documentList);
        LOGGER.info("Course details are indexed into Elasticsearch.");
    }
}
