package com.library.manage.service.impl;

import com.library.manage.dao.CommentDao;
import com.library.manage.model.dto.CommentDTO;
import com.library.manage.model.entity.Book;
import com.library.manage.model.entity.Comment;
import com.library.manage.model.param.CommentParam;
import com.library.manage.model.vo.BookVO;
import com.library.manage.model.vo.CommentVO;
import com.library.manage.service.BookService;
import com.library.manage.service.CommentService;
import com.library.manage.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentDao commentDao;
    @Autowired
    BookService bookService;

    @Override
    public List<CommentDTO> getBookComment(String ISBN) {
        return BeanUtil.entityListToDTOList(commentDao.findByISBN(ISBN), CommentDTO.class);
    }

    @Override
    public List<CommentVO> getStuComment(String stuno) {
        List<Comment> byAuthor = commentDao.findByAuthor(stuno);
        List<CommentVO> commentVOS = new ArrayList<>();
        byAuthor.forEach(comment -> {
            commentVOS.add(CommentVO.builder().comment(BeanUtil.entityToDTO(CommentDTO.class, comment)).book(bookService.getNormalBook(comment.getISBN())).build());
        });
        return commentVOS;
    }

    @Override
    @Transactional
    public CommentDTO postComment(CommentParam commentParam) {
        Book normalBook = bookService.getNormalBook(commentParam.getISBN());
        Assert.notNull(normalBook, "不能为空");
        Double score = normalBook.getScore();
        Integer scorenum = normalBook.getScorenum();
        score = (score * scorenum + commentParam.getScore())/(scorenum + 1);
        normalBook.setScore(score);
        normalBook.setScorenum(++scorenum);
        bookService.save(normalBook);
        return BeanUtil.entityToDTO(CommentDTO.class, commentDao.save(Objects.requireNonNull(BeanUtil.transformFrom(commentParam, Comment.class))));
    }

    @Transactional
    @Override
    public String deleteComment(Integer id) {
        commentDao.deleteById(id);
        return "删除成功";
    }

    @Transactional
    @Override
    public String deleteComments(List<Integer> ids) {
        commentDao.deleteBatch(ids);
        return "删除多条评论成功";
    }

    @Override
    public List<CommentDTO> getComment() {
        return BeanUtil.entityListToDTOList(commentDao.findAll(), CommentDTO.class);
    }
}
