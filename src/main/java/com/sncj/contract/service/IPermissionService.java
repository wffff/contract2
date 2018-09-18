package com.sncj.contract.service;


import com.sncj.contract.entity.PermissionEntity;
import org.springframework.data.domain.Page;

import java.util.List;


public interface IPermissionService {
    Page<PermissionEntity> page(Integer page, Integer limit);

    PermissionEntity save(String name, String description);

    PermissionEntity findOne(Integer id);

    List<PermissionEntity> findAll();
}
