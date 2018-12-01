package com.service.impl;

import com.dao.AlbumMapper;
import com.entity.Album;
import com.entity.Carousel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumMapper albumMapper;

    @Override
    public Map selectAll(int page, int rows) {
        PageHelper.startPage(page, rows);
        List list = albumMapper.selectAll();
        PageInfo<Carousel> pageInfo = new PageInfo<>(list);

        Map map = new HashMap();
        map.put("total", pageInfo.getTotal());
        map.put("rows", pageInfo.getList());
        return map;
    }

    @Override
    public void insert(Album album) {
        albumMapper.insert(album);
    }
}
