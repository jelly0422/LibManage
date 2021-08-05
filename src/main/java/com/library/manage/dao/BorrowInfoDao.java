package com.library.manage.dao;

import com.library.manage.model.entity.BorrowInfo;
import io.netty.resolver.CompositeNameResolver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowInfoDao extends JpaRepository<BorrowInfo, Integer> {

    List<BorrowInfo> findByISBN(String ISBN);

    List<BorrowInfo> findByBorrowstu(String borrowstu);

    Long countBorrowInfoByISBN(String ISBN);

    List<BorrowInfo> findByBorrowstuAndISBN(String stuno, String ISBN);
}
