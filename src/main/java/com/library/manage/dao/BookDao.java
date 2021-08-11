package com.library.manage.dao;

import com.library.manage.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BookDao extends JpaRepository<Book, Integer> {

    @Transactional
    @Modifying
    @Query(value = "update book set #{#key}=#{#val} where ISBN=#{ISBN}", nativeQuery = true)
    Integer updateBookInfo(@Param("key") String key,@Param("val") String val,@Param("ISBN") String ISBN);

    @Modifying
    @Query(value = "delete from book where ISBN=?1", nativeQuery = true)
    void deleteByISBNs(List<String> ISBNs);

    @Query(value = "select ISBN, score, score_num from book order by (score * 5 + score_num / 5) DESC", nativeQuery = true)
    List<Map<String, Object>> findScoreAndISBN();

    @Query(value = "select ISBN from book where category=?1", nativeQuery = true)
    List<String> getISBNByCategory(String category);

    Optional<Book> findByISBN(String isbn);
}
