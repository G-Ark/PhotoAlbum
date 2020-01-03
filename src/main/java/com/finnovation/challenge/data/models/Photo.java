package com.finnovation.challenge.data.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.UUID;

@Data
public class Photo {
    @JsonProperty("id")
    private Integer _id;

    @Indexed
    private String photoId = UUID.randomUUID().toString();

    private Integer albumId;

    private String title;

    private String url;

    private String thumbnailUrl;
}
