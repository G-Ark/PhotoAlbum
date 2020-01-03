package com.finnovation.challenge.search.response.dto;

import com.finnovation.challenge.data.models.Album;
import lombok.Data;

@Data
public class PhotoSearchResponseDTO extends BaseSearchResponseDTO {
    private Integer albumId;

    private Integer id;

    private String title;

    private String url;

    private String thumbnailUrl;
}
