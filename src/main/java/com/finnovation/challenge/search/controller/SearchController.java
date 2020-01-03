package com.finnovation.challenge.search.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.finnovation.challenge.search.response.dto.BaseSearchResponseDTO;
import com.finnovation.challenge.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;

@RestController
public class SearchController {
    @Autowired
    private SearchService searchService;

    @GetMapping("/api/v1/search")
    public ResponseEntity<BaseSearchResponseDTO> searchPhoto(@RequestParam("type") String type,
                                              @RequestParam("id") Integer id,
                                              @RequestParam(value = "album", required = false) Integer albumId) {
        BaseSearchResponseDTO base = searchService.search(type, albumId, id);
        if (base == null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(base, HttpStatus.OK);
    }

    @GetMapping("/api/v1/search/photo")
    public ResponseEntity<BaseSearchResponseDTO> searchPhoto(@RequestParam("albumId") Integer albumId) {
        BaseSearchResponseDTO base = searchService.search(albumId);
        if (base == null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(base, HttpStatus.OK);
    }
}
