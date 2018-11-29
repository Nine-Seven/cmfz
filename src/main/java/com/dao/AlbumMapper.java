package com.dao;

import com.entity.Album;

import java.util.List;

public interface AlbumMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Album record);

    List selectAll();

    int updateByPrimaryKey(Album record);
}