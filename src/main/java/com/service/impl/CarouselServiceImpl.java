package com.service.impl;

import com.dao.CarouselMapper;
import com.entity.Carousel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    private CarouselMapper carouselMapper;


    @Override
    public int insert(Carousel record) {
        return carouselMapper.insert(record);
    }

    @Override
    public Map selectAll(int page, int rows) {
        PageHelper.startPage(page, rows);
        List list = carouselMapper.selectAll();
        PageInfo<Carousel> pageInfo = new PageInfo<>(list);

        Map map = new HashMap();
        map.put("total", pageInfo.getTotal());
        map.put("rows", pageInfo.getList());
        return map;
    }

    @Override
    public void updateStatus(Carousel record) {
        carouselMapper.updateStatus(record);
    }
}
