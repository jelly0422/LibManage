package com.library.manage.service;

import com.library.manage.model.dto.FavorDTO;
import com.library.manage.model.entity.Book;
import com.library.manage.model.param.FavorParam;
import com.library.manage.model.vo.BookVO;
import com.library.manage.model.vo.FavorVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FavorService {

    FavorVO getFavorVO(String belongstu);

    String cancelFavor(FavorParam favorParam);

    Book addFavor(FavorParam favorParam);

    String addList(List<FavorParam> favorParams);

    String cancelList(List<FavorParam> favorParams);

    boolean isFavor(String ISBN, String stuno);

    List<BookVO> getStuCommend(String stuno);
}
