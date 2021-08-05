package com.library.manage.model.vo;

import com.library.manage.model.dto.BorrowInfoDTO;
import com.library.manage.model.dto.StudentDTO;
import com.library.manage.model.entity.BorrowInfo;
import com.library.manage.model.entity.Comment;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@Builder
public class StudentVO {

    private StudentDTO studentDTO;

    private List<CommentVO> comments;

    private List<BorrowInfoDTO> borrowInfos;

    private FavorVO favorVO;
}
