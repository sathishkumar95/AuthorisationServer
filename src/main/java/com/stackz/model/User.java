package com.stackz.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class User {

    @Id
    String id;

    String name;

    String username;

    String password;

    Integer age;
}
