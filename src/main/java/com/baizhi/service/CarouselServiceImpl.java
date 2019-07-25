package com.baizhi.service;

import com.baizhi.dao.CarouselDao;
import com.baizhi.entity.Carousel;
import com.baizhi.entity.CarouselSto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CarouselServiceImpl implements CarouselService{
    @Autowired
    private CarouselDao carouselDao;

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public CarouselSto queryAll(Integer page, Integer rows) {
        CarouselSto sto = new CarouselSto();
        sto.setPage(page); //存入当前页数

        System.out.println(page);


        Integer total = carouselDao.countCarousel();
        Integer pagetotal = total % rows == 0 ? (total / rows) : (total / rows + 1);
        sto.setRecords(total); //存入总页数
        sto.setTotal(pagetotal); //存入数据总条数

        System.out.println(total);
        System.out.println(pagetotal);

        Integer begin = (page-1)*rows+1;
        Integer end = page*rows;
        sto.setRows(carouselDao.selectAllCarousel(begin,end));
        System.out.println(carouselDao.selectAllCarousel(begin,end));

        return sto;
    }

    @Override
    public void addCarousel(Carousel carousel) {
        carouselDao.insertCarousel(carousel);
    }
}
