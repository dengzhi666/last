package com.baizhi.dao;

import com.baizhi.entity.Carousel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CarouselDao {
    void insertCarousel(Carousel carousel);
    Integer countCarousel();
    List<Carousel> selectAllCarousel(@Param("begin") Integer begin, @Param("end")Integer end);
}
