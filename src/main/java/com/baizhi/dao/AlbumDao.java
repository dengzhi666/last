package com.baizhi.dao;

import com.baizhi.entity.Album;
import com.baizhi.entity.Carousel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumDao {
    void insertAlbum(Album album);
    void updateAlbum(Album album);
    Integer countAlbum();
    List<Carousel> selectAllAlbum(@Param("begin") Integer begin, @Param("end") Integer end);

    Album selectOneAlbum(String id);
}
