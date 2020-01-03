package com.finnovation.challenge.data.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.UUID;

@Data
public class Album {
    @JsonProperty("id")
    private Integer _id;

    @Indexed
    private String albumId = UUID.randomUUID().toString();

    private Integer userId;

    private String title;
}
