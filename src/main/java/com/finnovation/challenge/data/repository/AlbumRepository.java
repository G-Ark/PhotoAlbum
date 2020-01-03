package com.finnovation.challenge.data.repository;

import com.finnovation.challenge.data.models.Album;
import org.springframework.data.repository.CrudRepository;

public interface AlbumRepository extends CrudRepository<Album, Integer> {
}
