package com.service;

import com.entity.Carousel;

import java.util.Map;

public interface CarouselService {
    int insert(Carousel record);

    Map selectAll(int page, int rows);

    void updateStatus(Carousel record);
}
