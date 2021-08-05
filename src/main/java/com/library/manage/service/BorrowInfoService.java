package com.library.manage.service;

import com.library.manage.model.dto.BorrowInfoDTO;
import com.library.manage.model.entity.BorrowInfo;
import com.library.manage.model.param.BorrowParam;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface BorrowInfoService {

    List<BorrowInfoDTO> stuGetBorrowInfo(String stuno);

    List<BorrowInfoDTO> adminGetBorrowInfo(String ISBN);

    BorrowInfoDTO postBorrowInfo(BorrowParam borrowParam);

    BorrowInfoDTO retBook(Integer id);

    List<BorrowInfoDTO> getBorrowInfo();

    boolean getBorrowStatus(String stuno, String ISBN);

    BorrowInfoDTO renewBook(Integer id);
}
