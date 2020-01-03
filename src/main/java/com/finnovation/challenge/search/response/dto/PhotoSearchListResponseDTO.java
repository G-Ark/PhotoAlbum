package com.finnovation.challenge.search.response.dto;

import lombok.Data;

import java.util.List;

@Data
public class PhotoSearchListResponseDTO extends BaseSearchResponseDTO {
    private String albumTitle;

    private Integer userId;

    List<PhotoSearchResponseDTO> photoSearchResponseDTOList;
}
