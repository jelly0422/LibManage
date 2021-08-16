package com.library.manage.controller;

import com.library.manage.model.param.FavorParam;
import com.library.manage.model.support.BaseResponse;
import com.library.manage.model.vo.FavorVO;
import com.library.manage.service.FavorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author jelly
 */
@RestController
@RequestMapping("/favor")
public class FavorController {

    @Autowired
    FavorService favorService;

    @ApiOperation("添加书本到收藏夹")
    @PostMapping("/add")
    public BaseResponse<String> addFavor(@RequestBody FavorParam favorParam){
        System.out.println(favorParam);
        return Optional.ofNullable(favorService.addFavor(favorParam)).isPresent() ? BaseResponse.ret("添加成功") : BaseResponse.ret("添加失败");
    }

    @ApiOperation("取消收藏")
    @PostMapping("/cancel")
    public BaseResponse<FavorVO> cancelFavor(@RequestBody FavorParam favorParam){
        System.out.println(favorParam);
        return BaseResponse.ok(favorService.cancelFavor(favorParam), favorService.getFavorVO(favorParam.getBelongstu()));
    }
}
