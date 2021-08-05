package com.library.manage.service.impl;

import com.library.manage.dao.BookDao;
import com.library.manage.dao.FavorDao;
import com.library.manage.model.entity.Book;
import com.library.manage.model.entity.Favor;
import com.library.manage.model.param.FavorParam;
import com.library.manage.model.vo.BookVO;
import com.library.manage.model.vo.FavorVO;
import com.library.manage.service.BookService;
import com.library.manage.service.FavorService;
import com.library.manage.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service
public class FavorServiceImpl implements FavorService {

    @Autowired
    FavorDao favorDao;
    @Autowired
    BookDao bookDao;
    @Autowired
    BookService bookService;

    @Override
    public FavorVO getFavorVO(String belongstu) {
        List<Favor> favors = favorDao.findByBelongstu(belongstu);
        List<BookVO> bookVOList = new ArrayList<>();
        for (Favor favor : favors) {
            bookVOList.add(bookService.getBook(favor.getISBN()));
        }
        return FavorVO.builder().stu(belongstu).books(bookVOList).build();
    }

    @Transactional
    @Override
    public String cancelFavor(FavorParam favorParam) {
        favorDao.deleteFavorByBelongstuAndISBN(favorParam.getBelongstu(), favorParam.getISBN());
        return "取消收藏成功";
    }

    @Transactional
    @Override
    public Book addFavor(FavorParam favorParam) {
        Assert.notNull(favorParam, "不能为空");
        if (favorDao.existsByBelongstuAndISBN(favorParam.getBelongstu(), favorParam.getISBN())){
            return null;
        }
        Favor save = favorDao.save(Objects.requireNonNull(BeanUtil.transformFrom(favorParam, Favor.class)));
        return bookDao.findById(save.getISBN()).orElse(null);
    }

    @Transactional
    @Override
    public String addList(List<FavorParam> favorParams) {
        return null;
    }

    @Transactional
    @Override
    public String cancelList(List<FavorParam> favorParams) {
        return null;
    }

    @Override
    public boolean isFavor(String ISBN, String stuno) {
        return favorDao.existsByBelongstuAndISBN(stuno, ISBN);
    }

    @Override
    public List<BookVO> getStuCommend(String stuno) {
        List<BookVO> bookVOS = new ArrayList<>();
        List<Map<String, Object>> favorCategory = favorDao.getFavorCategory(stuno);
        if (favorCategory == null){
            List<BookVO> allBook = bookService.getAllBook(stuno);
            Iterator<BookVO> iterator = allBook.iterator();
            int count = 1;
            while(iterator.hasNext()){
                if (count <= 5){
                    bookVOS.add(iterator.next());
                }
                count++;
            }
        }else{
            Iterator<Map<String, Object>> iterator = favorCategory.iterator();
            int count1 = 0;
            while(iterator.hasNext()){
                if (count1 < 2){
                    List<String> hobby = bookService.getISBNByCategory((String) iterator.next().get("hobby"));
                    Iterator<String> iterator1 = hobby.iterator();
                    int count = 1;
                    while(iterator1.hasNext()){
                        if (count <= 3 ){
                            bookVOS.add(bookService.getBook(iterator1.next()));
                        }
                        count++;
                    }
                    count1++;
                }

            }
        }

        return bookVOS;
    }
}
