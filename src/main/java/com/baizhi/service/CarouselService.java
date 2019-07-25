package com.baizhi.service;

import com.baizhi.entity.Carousel;
import com.baizhi.entity.CarouselSto;

public interface CarouselService {
    void addCarousel(Carousel carousel);
    CarouselSto queryAll(Integer page, Integer rows);
}
