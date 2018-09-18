package com.sncj.contract.service.impl;

import com.sncj.contract.entity.PermissionEntity;
import com.sncj.contract.entity.RoleEntity;
import com.sncj.contract.repository.IPermissionRepository;
import com.sncj.contract.repository.IRoleRepository;
import com.sncj.contract.service.IPermissionService;
import com.sncj.contract.service.IRoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class RoleService implements IRoleService {
    @Resource
    private IRoleRepository iRoleRepository;

    @Resource
    private IPermissionService iPermissionService;

    public RoleEntity save(String name, String description) {
        RoleEntity r=new RoleEntity();
        r.setName(name);
        r.setDescription(description);
        r.setTime(new Date());

        return iRoleRepository.save(r);
    }

    public Page<RoleEntity> page(Integer page, Integer limit) {
        return iRoleRepository.findAll(PageRequest.of(page-1,limit));
    }

    @Override
    public RoleEntity enabled(Integer roleId, Integer permissionId, boolean enabled) {
        RoleEntity r=iRoleRepository.findById(roleId).get();
        PermissionEntity p=iPermissionService.findOne(permissionId);

        if (enabled){
            r.getPermission().add(p);
        }else {
            r.getPermission().remove(p);
        }
        return iRoleRepository.save(r);
    }

    @Override
    public RoleEntity findOne(Integer id) {
        return iRoleRepository.findById(id).get();
    }

    @Override
    public List<RoleEntity> findAll() {
        return iRoleRepository.findAllByDelFalse();
    }
}
