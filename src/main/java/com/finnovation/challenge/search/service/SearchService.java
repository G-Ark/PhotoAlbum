package com.finnovation.challenge.search.service;

import com.finnovation.challenge.ChallengeException;
import com.finnovation.challenge.data.models.Album;
import com.finnovation.challenge.data.models.Photo;
import com.finnovation.challenge.data.repository.AlbumRepository;
import com.finnovation.challenge.data.repository.PhotoRepository;
import com.finnovation.challenge.search.response.dto.AlbumSearchResponseDTO;
import com.finnovation.challenge.search.response.dto.BaseSearchResponseDTO;
import com.finnovation.challenge.search.response.dto.PhotoSearchListResponseDTO;
import com.finnovation.challenge.search.response.dto.PhotoSearchResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {
    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private PhotoRepository photoRepository;

    public BaseSearchResponseDTO search(String type, Integer albumId, Integer id) {
        if ("album".equalsIgnoreCase(type)) {
            Album album = albumRepository.findById(id)
                    .orElseThrow(() -> new ChallengeException("Not found!"));
            if (album != null) {
                AlbumSearchResponseDTO albumSearchResponseDTO = new AlbumSearchResponseDTO();
                albumSearchResponseDTO.setId(id);
                albumSearchResponseDTO.setTitle(album.getTitle());
                albumSearchResponseDTO.setUserId(album.getUserId());
                return albumSearchResponseDTO;
            }
        } else if ("photo".equalsIgnoreCase(type)) {
            if (albumId != null) {
                Photo photo = photoRepository.findById(id)
                        .orElseThrow(() -> new ChallengeException("Not found!"));
                if (!photo.getAlbumId().equals(albumId)) {
                    throw new ChallengeException("Given albumId is incorrect");
                }
                PhotoSearchResponseDTO photoSearchResponseDTO = new PhotoSearchResponseDTO();
                photoSearchResponseDTO.setAlbumId(albumId);
                photoSearchResponseDTO.setId(id);
                photoSearchResponseDTO.setThumbnailUrl(photo.getThumbnailUrl());
                photoSearchResponseDTO.setTitle(photo.getTitle());
                photoSearchResponseDTO.setUrl(photo.getUrl());
                return photoSearchResponseDTO;
            } else {
                throw new ChallengeException("AlbumId not provided");
            }
        }
        throw new ChallengeException("Unknown type provided");
    }

    public BaseSearchResponseDTO search(Integer albumId) {
        Album album = albumRepository.findById(albumId).orElseThrow(() -> new ChallengeException("AlbumId not found"));

        List<Photo> photoList = photoRepository.findByAlbumId(albumId);
        List<PhotoSearchResponseDTO> photoSearchResponseDTOList = new ArrayList<>();
        for (Photo photo: photoList) {
            PhotoSearchResponseDTO photoSearchResponseDTO = new PhotoSearchResponseDTO();
            photoSearchResponseDTO.setAlbumId(albumId);
            photoSearchResponseDTO.setId(photo.get_id());
            photoSearchResponseDTO.setThumbnailUrl(photo.getThumbnailUrl());
            photoSearchResponseDTO.setTitle(photo.getTitle());
            photoSearchResponseDTO.setUrl(photo.getUrl());
            photoSearchResponseDTOList.add(photoSearchResponseDTO);
        }

        PhotoSearchListResponseDTO photoSearchResponseDTO = new PhotoSearchListResponseDTO();
        photoSearchResponseDTO.setPhotoSearchResponseDTOList(photoSearchResponseDTOList);
        photoSearchResponseDTO.setUserId(album.getUserId());
        photoSearchResponseDTO.setAlbumTitle(album.getTitle());

        return photoSearchResponseDTO;
    }
}
