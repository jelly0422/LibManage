package com.library.manage.service.impl;

import com.library.manage.dao.BorrowInfoDao;
import com.library.manage.model.dto.BorrowInfoDTO;
import com.library.manage.model.entity.BorrowInfo;
import com.library.manage.model.enums.IsInTime;
import com.library.manage.model.param.BorrowParam;
import com.library.manage.service.BookService;
import com.library.manage.service.BorrowInfoService;
import com.library.manage.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class BorrowInfoServiceImpl implements BorrowInfoService {

    @Autowired
    BorrowInfoDao borrowInfoDao;
    @Autowired
    BookService bookService;

    @Override
    public List<BorrowInfoDTO> stuGetBorrowInfo(String stuno) {
        return BeanUtil.entityListToDTOList(borrowInfoDao.findByBorrowstu(stuno), BorrowInfoDTO.class);
    }

    @Override
    public List<BorrowInfoDTO> adminGetBorrowInfo(String ISBN) {

        return BeanUtil.entityListToDTOList(borrowInfoDao.findByISBN(ISBN), BorrowInfoDTO.class);
    }

    @Transactional
    @Override
    public BorrowInfoDTO postBorrowInfo(BorrowParam borrowParam) {
        if (bookService.stockIsEnough(borrowParam.getISBN())){
            bookService.updateBorrow(borrowParam.getISBN());
            return BeanUtil.entityToDTO(BorrowInfoDTO.class, borrowInfoDao.saveAndFlush(Objects.requireNonNull(BeanUtil.transformFrom(borrowParam, BorrowInfo.class))));
        }
        return null;
    }

    @Override
    public BorrowInfoDTO retBook(Integer id) {
        //得到对应id的借阅记录
        Optional<BorrowInfo> borrowInfo = borrowInfoDao.findById(id);
        //设置归还时间并判断是否超时
        BorrowInfo borrowInfo1 = borrowInfoDao.saveAndFlush(borrowInfo.map(var -> {
            Date date = new Date();
            //获取借阅时间和归还时间日历
            Calendar instance = Calendar.getInstance();
            Calendar instance1 = Calendar.getInstance();
            instance.setTime(date);
            instance1.setTime(var.getBorrowtime());
            //获取两个日历天数相减判断是否超时
            if (instance.get(Calendar.DAY_OF_YEAR) - instance1.get(Calendar.DAY_OF_YEAR) < var.getReadtime()) {
                var.setIsInTime(IsInTime.YES);
            } else
                var.setIsInTime(IsInTime.NO);
            var.setRettime(date);
            return var;
        }).get());
        //更新书籍库存，库存加一，已借阅数减一
        bookService.updateRet(borrowInfo.get().getISBN());
        return BeanUtil.entityToDTO(BorrowInfoDTO.class, borrowInfo1);
    }

    @Override
    public List<BorrowInfoDTO> getBorrowInfo() {
        return BeanUtil.entityListToDTOList(borrowInfoDao.findAll(), BorrowInfoDTO.class);
    }

    @Override
    public boolean getBorrowStatus(String stuno, String ISBN) {
        List<BorrowInfo> byBorrowstu = borrowInfoDao.findByBorrowstuAndISBN(stuno, ISBN);
        if (byBorrowstu!=null){
            for (BorrowInfo info : byBorrowstu) {
                if (info.getRettime() == null && info.getBorrowstu().equals(stuno)){
                    return false;
                }
            }
        }

        return true;
    }

    @Transactional
    @Override
    public BorrowInfoDTO renewBook(Integer id) {
        Optional<BorrowInfo> byId = borrowInfoDao.findById(id);
        byId.ifPresent(borrowInfo -> {
            borrowInfo.setRenew(true);
            borrowInfo.setReadtime(borrowInfo.getReadtime() + 30);
        });
        return BeanUtil.entityToDTO(BorrowInfoDTO.class, borrowInfoDao.saveAndFlush(byId.get()));
    }
}
