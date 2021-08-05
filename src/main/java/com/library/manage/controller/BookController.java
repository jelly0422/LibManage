package com.library.manage.controller;

import com.library.manage.model.support.BaseResponse;
import com.library.manage.model.vo.BookVO;
import com.library.manage.service.BookService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @ApiOperation("获取所有书籍以及评论和分类")
    @GetMapping("/get")
    public BaseResponse<List<BookVO>> retAllBook(String stuno){
        return BaseResponse.ok(bookService.getAllBook(stuno));
    }

    @ApiOperation("获取一本书籍以及评论和分类")
    @GetMapping("/getone")
    public BaseResponse<BookVO> retBook(@RequestParam("ISBN") String ISBN){
        return BaseResponse.ok(bookService.getBook(ISBN));
    }

    @ApiOperation("获取热门")
    @GetMapping("/gethot")
    public BaseResponse<List<BookVO>> getHot(){
        return BaseResponse.ok(bookService.getHotBooks());
    }
}
