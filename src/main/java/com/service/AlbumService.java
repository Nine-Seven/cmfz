package com.service;

import com.entity.Album;

import java.util.Map;

public interface AlbumService {
    Map selectAll(int page, int rows);

    void insert(Album album);
}
