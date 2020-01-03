package com.finnovation.challenge.search.response.dto;

import lombok.Data;

@Data
public class AlbumSearchResponseDTO extends BaseSearchResponseDTO {
    private Integer userId;

    private Integer id;

    private String title;
}
