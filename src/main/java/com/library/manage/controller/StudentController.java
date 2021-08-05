package com.library.manage.controller;

import com.library.manage.model.dto.BorrowInfoDTO;
import com.library.manage.model.dto.CommentDTO;
import com.library.manage.model.dto.StudentDTO;
import com.library.manage.model.entity.BorrowInfo;
import com.library.manage.model.entity.Comment;
import com.library.manage.model.enums.UserStatus;
import com.library.manage.model.param.*;
import com.library.manage.model.param.emailParam.CodeParam;
import com.library.manage.model.param.emailParam.EmailParam;
import com.library.manage.model.support.BaseResponse;
import com.library.manage.model.vo.BookVO;
import com.library.manage.model.vo.StudentVO;
import com.library.manage.service.BorrowInfoService;
import com.library.manage.service.CommentService;
import com.library.manage.service.FavorService;
import com.library.manage.service.StudentService;
import com.library.manage.util.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Api("student api")
@CrossOrigin
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;
    @Autowired
    CommentService commentService;
    @Autowired
    BorrowInfoService borrowInfoService;
    @Autowired
    FavorService favorService;


    @ApiOperation("学生登录，登录成功返回")
    @PostMapping(value = "/login")
    public BaseResponse<StudentDTO> stuLogin(@RequestBody LoginParam loginParam){
        StudentDTO studentDTO = studentService.stuLogin(loginParam);
        return Optional.ofNullable(studentDTO).isPresent() ?
                (studentDTO.getStatus().equals(UserStatus.FORBIDDEN) ? BaseResponse.ret("用户被禁用") : BaseResponse.ok("登录成功",studentDTO)) : BaseResponse.ret("用户名或密码错误");
    }

    @ApiOperation("学生注册，返回中注册信息")
    @PostMapping("/regist")
    public String stuRegist(@RequestBody StuRegistParam stuRegistParam){
        return studentService.stuRegist(stuRegistParam);
    }

    @ApiOperation("检查邮箱验证码")
    @GetMapping("/check")
    public String checkCode(String code, HttpServletRequest request){
        String code1 = (String)request.getSession().getAttribute("code");
        if (code1 == null){
            return "验证码过期";
        }
        if (code.equals(code1)){
            return "成功";
        }
        return "验证码错误";
    }

    @ApiOperation("发送邮箱验证码")
    @PostMapping("/sendcode")
    public BaseResponse<String> sendEmailCode(@RequestBody EmailParam email){
        return BaseResponse.ok(studentService.sendEmail(email.getEmail()));
    }

    @ApiOperation("修改个人信息")
    @PostMapping("/update")
    public BaseResponse<StudentDTO> updateStu(@RequestBody UpdateStuParam updateStuParam){
        StudentDTO studentDTO1 = studentService.updateStuInfo(updateStuParam.getStuno(), updateStuParam.getKey(), updateStuParam.getVal());
        return Optional.ofNullable(studentDTO1).isPresent() ? BaseResponse.ok("修改成功", studentDTO1) : BaseResponse.ret("修改失败");
    }

    @ApiOperation("修改密码")
    @PostMapping("/resetpwd")
    public BaseResponse<String> resetPwd(@RequestBody PasswordParam param){
        System.out.println(param);
        return BaseResponse.ret(studentService.updatePassword(param.getOldPwd(), param.getNewPwd(), param.getId()));
    }

    @ApiOperation("获取学生借阅记录，收藏等信息")
    @GetMapping("/intocenter")
    public BaseResponse<StudentVO> getStuInfo(StudentParam studentParam){
        return BaseResponse.ok(studentService.intoStuCenter(BeanUtil.transformFrom(studentParam, StudentDTO.class)));
    }

    @ApiOperation("学生发评论")
    @PostMapping("/postcomment")
    public BaseResponse<CommentDTO> postComment(@RequestBody CommentParam commentParam){
        return BaseResponse.ok(commentService.postComment(commentParam));
    }

    @ApiOperation("删除一条评论")
    @PostMapping("/deleteone")
    public String deleteComment(@RequestBody IdParam id){
        return commentService.deleteComment(id.getId());
    }

    @ApiOperation("删除多条评论")
    @PostMapping("/multidelete")
    public String deleteComments(@RequestBody MultiDeleteParam ids){
        return commentService.deleteComments(ids.getIds());
    }

    @ApiOperation("归还书籍")
    @PostMapping("/retbook")
    public BorrowInfoDTO retBook(@RequestBody IdParam idParam){
        return borrowInfoService.retBook(idParam.getId());
    }

    @ApiOperation("借书")
    @PostMapping("/borrow")
    public BorrowInfoDTO borrowBook(@RequestBody BorrowParam borrowParam){
        return borrowInfoService.postBorrowInfo(borrowParam);
    }

    @ApiOperation("/续借")
    @PostMapping("/renew")
    public BaseResponse<BorrowInfoDTO> renewBook(@RequestBody IdParam idParam){
        return BaseResponse.ok(borrowInfoService.renewBook(idParam.getId()));
    }

    @ApiOperation("获取学生推荐")
    @GetMapping("/sturecommend")
    public BaseResponse<List<BookVO>> getStuRecommend(@RequestBody StudentParam studentParam){
        return BaseResponse.ok(favorService.getStuCommend(studentParam.getStuno()));
    }
}
