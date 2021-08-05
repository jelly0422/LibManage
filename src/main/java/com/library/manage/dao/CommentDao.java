package com.library.manage.dao;

import com.library.manage.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentDao extends JpaRepository<Comment, Integer> {

    List<Comment> findByISBN(String ISBN);

    List<Comment> findByAuthor(String author);


    @Query(value = "delete from comment where id=?1", nativeQuery = true)
    void deleteCommentsById(List<Integer> ids);

    @Transactional
    @Modifying
    @Query("delete from Comment c where c.id in (?1)")
    void deleteBatch(List<Integer> ids);
}
