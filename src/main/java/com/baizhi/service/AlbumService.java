package com.baizhi.service;

import com.baizhi.entity.Album;
import com.baizhi.entity.CarouselSto;

import java.util.Map;

public interface AlbumService {
    void addAlbum(Album album);
    Map<String,Object> queryAll(Integer page, Integer rows);
}
