package com.sncj.contract.controller;

import com.sncj.contract.baseconfig.ReturnMessage;
import com.sncj.contract.entity.PermissionEntity;
import com.sncj.contract.entity.RoleEntity;
import com.sncj.contract.service.IPermissionService;
import com.sncj.contract.service.IRoleService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("permission")
public class PermissionController {
    @Resource
    private IPermissionService iPermissionService;
    @Resource
    private IRoleService iRoleService;
    @RequestMapping("page")
    @ResponseBody
    public ReturnMessage<List<PermissionEntity>> page(@RequestParam(value = "page",defaultValue = "1")Integer page,@RequestParam(value = "limit",defaultValue = "20")Integer limit){
        Page<PermissionEntity> p =iPermissionService.page(page,limit);
        return ReturnMessage.success((int) p.getTotalElements(), p.getContent());
    }

    @RequestMapping("save")
    @ResponseBody
    public ReturnMessage<PermissionEntity> save(String name, String description){
        PermissionEntity p = iPermissionService.save(name,description);
        return ReturnMessage.success(0,p);
    }

    @RequestMapping("findAll")
    @ResponseBody
    public ReturnMessage<List<PermissionEntity>> findAll(Integer id){
        RoleEntity one = iRoleService.findOne(id);
        Set<PermissionEntity> permissionHas = one.getPermission();
        List<PermissionEntity> all = iPermissionService.findAll();
        for (PermissionEntity p:all){
            if (permissionHas.contains(p)){
                p.setEnabled(true);
            }
        }
        return ReturnMessage.success(all.size(),all);
    }

    @RequestMapping("grant")
    @ResponseBody
    public ReturnMessage grant(Integer roleId,Integer permissionId,boolean grant){
        try {
            RoleEntity enabled = iRoleService.enabled(roleId, permissionId, grant);//??
            return ReturnMessage.success(1,enabled);
        }catch (Exception e){
            return ReturnMessage.failed();
        }
    }
}
