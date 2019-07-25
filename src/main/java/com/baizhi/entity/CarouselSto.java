package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarouselSto implements Serializable {
    private Integer page;
    private Integer total;
    private Integer records;
    private List<Carousel> rows;


}
