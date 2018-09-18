package com.sncj.contract.controller;


import com.sncj.contract.baseconfig.ReturnMessage;
import com.sncj.contract.entity.UserEntity;
import com.sncj.contract.service.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("user")
//@Secured(value = "ROLE_USER")
public class UserController {
    @Resource
    private IUserService iUserService;

    @RequestMapping("save")
    @ResponseBody
    @Secured(value = "ROLE_USER_ADD")
    public ReturnMessage<UserEntity> save(String username,String password,String fullname) {
        UserEntity u = iUserService.save(username, password, fullname);
        if(u.getExist()){
            return ReturnMessage.failed("该用户名已存在，请重新输入用户名！");
        }
        return ReturnMessage.success(0, u);
    }

    @RequestMapping("page")
    @ResponseBody
    public ReturnMessage<List<UserEntity>> page(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "limit", defaultValue = "20") Integer limit) {
        Page<UserEntity> p = iUserService.page(page, limit);
        return ReturnMessage.success((int) p.getTotalElements(), p.getContent());
    }

    @RequestMapping("check")
    @ResponseBody
    public ReturnMessage<UserEntity> check(String username) {
        UserEntity u = iUserService.findByUsername(username);
        return ReturnMessage.success(0, u);
    }
}
