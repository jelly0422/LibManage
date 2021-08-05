package com.library.manage.service;

import com.library.manage.model.dto.CommentDTO;
import com.library.manage.model.entity.Comment;
import com.library.manage.model.param.CommentParam;
import com.library.manage.model.vo.CommentVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {

    List<CommentDTO> getBookComment(String ISBN);

    List<CommentVO> getStuComment(String stuno);

    CommentDTO postComment(CommentParam commentParam);

    String deleteComment(Integer id);

    String deleteComments(List<Integer> id);

    List<CommentDTO> getComment();
}
