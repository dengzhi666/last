package com.baizhi.service;


import com.baizhi.entity.Chapter;

import java.util.Map;

public interface ChapterService {

    Map<String,Object> queryAll(Integer page,Integer rows,String albumId);

    void addChapter(Chapter chapter);
}
