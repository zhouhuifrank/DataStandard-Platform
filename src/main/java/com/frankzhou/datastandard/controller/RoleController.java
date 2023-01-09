package com.frankzhou.datastandard.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: this.FrankZhou
 * @date: 2022/12/28
 * @description: 角色权限管理前端控制器
 */
@Api(tags = {"角色权限管理"})
@RestController
@RequestMapping("/author")
public class RoleController {

    @RequestMapping("/test")
    public String sayHello() {
        return "hello,author";
    }
}
