package com.baizhi.service;


import com.baizhi.dao.AlbumDao;
import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService{
    @Autowired
    private ChapterDao chapterDao;
    @Autowired
    private AlbumDao albumDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> queryAll(Integer page, Integer rows, String albumId) {
        Map<String,Object> map = new HashMap<>();
        Integer records = chapterDao.countChapter(albumId);
        Integer total = records % rows ==0 ? records/rows : records/rows+1;

        Integer begin = (page-1)*rows+1;
        Integer end = page*rows;
        List<Chapter> chapters = chapterDao.selectAllChapter(begin, end, albumId);
        map.put("page",page);  //存入当前页数
        map.put("records",records);  //存入总条数
        map.put("total",total);  //存入总页数
        map.put("rows",chapters);  //存入总页数

        return map;
    }

    @Override
    public void addChapter(Chapter chapter) {
        chapterDao.insertChapter(chapter);
        Album album = albumDao.selectOneAlbum(chapter.getAlbumId());
        album.setCount(album.getCount()+1);
        albumDao.updateAlbum(album);

    }
}
