package de.bag.mongotest;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class ExampleDAO {
    @Id
    private String id;

    private String data;

    // Constructors, Getters, and Setters
}
