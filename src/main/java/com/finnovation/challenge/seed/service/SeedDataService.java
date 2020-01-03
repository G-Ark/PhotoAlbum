package com.finnovation.challenge.seed.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finnovation.challenge.data.models.Album;
import com.finnovation.challenge.data.models.Photo;
import com.finnovation.challenge.data.repository.AlbumRepository;
import com.finnovation.challenge.data.repository.PhotoRepository;
import com.finnovation.challenge.seed.response.dto.AlbumSeedResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class SeedDataService {
    @Value("${album.seed.api}")
    private String albumSeedApiEndpoint;

    @Value("${photo.listing.api}")
    private String photoListingApi;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private PhotoRepository photoRepository;

    private static RestTemplate restTemplate = new RestTemplate();
    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public void seedData() throws JsonProcessingException {
        List<Album> albumList = getAllAlbums();
        for (Album album: albumList) {
            getPhotosForAlbums(album);
        }
    }

    private void getPhotosForAlbums(Album album) throws JsonProcessingException {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(photoListingApi)
                .queryParam("albumId", album.get_id());

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                builder.toUriString(),
                String.class
        );
        List<Photo> photoList = objectMapper.readValue(responseEntity.getBody(), new TypeReference<List<Photo>>() {
        });
        photoList.forEach(photo -> photo.setAlbumId(album.get_id()));
        photoRepository.saveAll(photoList);
    }

    private List<Album> getAllAlbums() throws JsonProcessingException {
        ResponseEntity<String> stringResponse = restTemplate.getForEntity(albumSeedApiEndpoint, String.class);
        List<Album> albumList = objectMapper.readValue(
                stringResponse.getBody(),
                new TypeReference<List<Album>>() {}
                );

        albumRepository.saveAll(albumList);
        return albumList;
    }
}