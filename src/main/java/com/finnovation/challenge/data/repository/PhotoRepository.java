package com.finnovation.challenge.data.repository;

import com.finnovation.challenge.data.models.Photo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PhotoRepository extends CrudRepository<Photo, Integer> {
    List<Photo> findByAlbumId(Integer albumId);
}
