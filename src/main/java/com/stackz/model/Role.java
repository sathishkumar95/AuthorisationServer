package com.stackz.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
public class Role {

    @Id
    String id;

    String username;

    List<String> permissions;

    public Role(String username, List<String> permissions) {
        this.username = username;
        this.permissions = permissions;
    }
}
