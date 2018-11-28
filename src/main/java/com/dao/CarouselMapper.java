package com.dao;

import com.entity.Carousel;

import java.util.List;

public interface CarouselMapper {

    int insert(Carousel record);

    List selectAll();

    int updateStatus(Carousel record);
}