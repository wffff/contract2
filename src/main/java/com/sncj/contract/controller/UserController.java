package com.sncj.contract.controller;


import com.sncj.contract.baseconfig.ReturnMessage;
import com.sncj.contract.entity.UserEntity;
import com.sncj.contract.service.IUserService;
import org.springframework.data.domain.Page;
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
public class UserController {
    @Resource
    private IUserService iUserService;

    @RequestMapping("save")
    public void save(String username, String password, String fullname, HttpServletResponse response) throws IOException {
        UserEntity u = iUserService.save(username, password, fullname);
        response.sendRedirect("/main?page=user/register");
    }

    @RequestMapping("page")
    @ResponseBody
    public ReturnMessage<List<UserEntity>> page(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "limit", defaultValue = "20") Integer limit) {
        Page<UserEntity> p = iUserService.page(page, limit);
        return ReturnMessage.success((int) p.getTotalElements(), p.getContent());
    }
}
