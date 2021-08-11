package com.library.manage.controller;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.library.manage.model.support.BaseResponse;
import com.library.manage.model.vo.BookVO;
import com.library.manage.service.BookService;
import io.swagger.annotations.ApiOperation;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

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
        List<BookVO> hot = null;
        ListOperations<String, Object> opsForList = redisTemplate.opsForList();
        Long size;
        if (redisTemplate.hasKey("hot") && (size = opsForList.size("hot")) > 0){
            hot = new ArrayList<>();
            List<Object> range = opsForList.range("hot", 0, size - 1);
//            System.out.println(range);
            hot = range.stream().map(o -> (BookVO)o).collect(Collectors.toList());
        }else{
            hot = bookService.getHotBooks();
        }
        return BaseResponse.ok(hot);
    }
}
