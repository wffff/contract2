package com.sncj.contract.service;

import com.sncj.contract.entity.RoleEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IRoleService {
    RoleEntity save(String name, String description);

    Page<RoleEntity> page(Integer page, Integer limit);

    RoleEntity enabled(Integer roleId, Integer permissionId, boolean enabled);//boolean enabled就是从PermissionController传过来的grant

    RoleEntity findOne(Integer id);

    List<RoleEntity> findAll();
}
