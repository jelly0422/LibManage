package com.library.manage.controller;

import com.library.manage.model.dto.AdminDTO;
import com.library.manage.model.dto.BorrowInfoDTO;
import com.library.manage.model.dto.CommentDTO;
import com.library.manage.model.entity.Book;
import com.library.manage.model.entity.BorrowInfo;
import com.library.manage.model.entity.Comment;
import com.library.manage.model.entity.Student;
import com.library.manage.model.param.*;
import com.library.manage.model.support.BaseResponse;
import com.library.manage.model.vo.BookVO;
import com.library.manage.service.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.service.ApiListing;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;
    @Autowired
    BookService bookService;
    @Autowired
    StudentService studentService;
    @Autowired
    BorrowInfoService borrowInfoService;
    @Autowired
    CommentService commentService;

    @ApiOperation("管理员登录")
    @PostMapping("/login")
    public BaseResponse<AdminDTO> adminLogin(@RequestBody AdminParam adminParam){
        return BaseResponse.ok(adminService.adminLogin(adminParam.getAccnum(), adminParam.getPassword()));
    }

    @ApiOperation("管理员重置密码")
    @PostMapping("/resetpwd")
    public String adminResetpwd(@RequestBody PasswordParam passwordParam){
        return adminService.adminResetPwd(passwordParam.getNewPwd(), passwordParam.getOldPwd(), passwordParam.getId());
    }

    @ApiOperation("添加书籍")
    @PostMapping(value = "/addbook")
    public BaseResponse<Book> addBook(@RequestBody BookParam bookParam){
//        Assert.notNull(multipartFile, "图片不能为空");

        return BaseResponse.ok(bookService.addBook(bookParam));
    }

    @ApiOperation("修改用户账号状态")
    @PostMapping("/updatestatus")
    public BaseResponse<Student> update(@RequestBody UpdateStuStatusParam stuStatusParam){
        return BaseResponse.ok(studentService.updateStatus(stuStatusParam));
    }

    @ApiOperation("获取所有用户")
    @GetMapping("getallstu")
    public BaseResponse<List<Student>> getAllStu(){
        return BaseResponse.ok(studentService.getAll());
    }

    @ApiOperation("删除书本")
    @PostMapping("/deletebook")
    public String deleteBook(@RequestBody MultiDeleteBookParam deleteBookParam){
        return bookService.deleteBooks(deleteBookParam.getIsbns());
    }

    @ApiOperation("更新书本")
    @PostMapping("/updatebook")
    public BaseResponse<String> updateBook(@RequestBody BookParam bookParam){//updateBookParam.getBooks()
        return BaseResponse.ok(bookService.updateBook(bookParam));
    }

    @ApiOperation("获取所有借阅信息")
    @GetMapping("/getborrow")
    public BaseResponse<List<BorrowInfoDTO>> getBorrowInfo(){
     return BaseResponse.ok(borrowInfoService.getBorrowInfo());
    }

    @GetMapping("getcomment")
    public BaseResponse<List<CommentDTO>> getComment(){
        return BaseResponse.ok(commentService.getComment());
    }

    @ApiOperation("上传图片")
    @PostMapping("/image")
    public String uploadImage(@RequestParam("file") MultipartFile file, String isbn){
        Assert.notNull(file, "图片不能为空");
        return bookService.uploadImage(file, isbn);
    }
}
