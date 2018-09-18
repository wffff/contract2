package com.sncj.contract.controller;

import com.sncj.contract.baseconfig.ReturnMessage;
import com.sncj.contract.entity.RoleEntity;
import com.sncj.contract.entity.UserEntity;
import com.sncj.contract.service.IPermissionService;
import com.sncj.contract.service.IRoleService;
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
import java.util.Set;

@Controller
@RequestMapping("role")
public class RoleController {
    @Resource
    private IRoleService iRoleService;
    @Resource
    private IUserService iUserService;

    @RequestMapping("save")
    @ResponseBody
    public ReturnMessage<RoleEntity> save(String name,String description) {
        RoleEntity r = iRoleService.save(name, description);
        return ReturnMessage.success(0, r);
    }

    @RequestMapping("page")
    @ResponseBody
    public ReturnMessage<List<RoleEntity>> page(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "limit", defaultValue = "20") Integer limit) {
        Page<RoleEntity> p = iRoleService.page(page, limit);
        return ReturnMessage.success((int) p.getTotalElements(), p.getContent());
    }

    @RequestMapping("findAll")
    @ResponseBody
    public ReturnMessage<List<RoleEntity>> findAll(Integer id){
        UserEntity one = iUserService.findOne(id);
        Set<RoleEntity> roles = one.getRole();
        List<RoleEntity> all = iRoleService.findAll();
        for (RoleEntity r:all){
            if(roles.contains(r)){
                r.setEnabled(true);
            }
        }
        return ReturnMessage.success(all.size(),all);
    }

    @RequestMapping("grant")
    @ResponseBody
    public ReturnMessage grant(Integer userId,Integer roleId,boolean grant){
        try {
            UserEntity enabled = iUserService.enabled(userId, roleId, grant);//??
            return ReturnMessage.success(1,enabled);
        }catch (Exception e){
            return ReturnMessage.failed();
        }
    }

}
