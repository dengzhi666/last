package com.baizhi.dao;

import com.baizhi.entity.Chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChapterDao {
    Integer countChapter(String albumId);
    List<Chapter> selectAllChapter(@Param("begin")Integer begin,@Param("end")Integer end,@Param("albumId") String albumId);
    void insertChapter(Chapter chapter);

}
