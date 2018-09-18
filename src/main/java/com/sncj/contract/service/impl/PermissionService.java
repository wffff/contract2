package com.sncj.contract.service.impl;

import com.sncj.contract.entity.PermissionEntity;
import com.sncj.contract.repository.IPermissionRepository;
import com.sncj.contract.service.IPermissionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class PermissionService implements IPermissionService {
    @Resource
    private IPermissionRepository iPermissionRepository;

    public Page<PermissionEntity> page(Integer page, Integer limit) {
        return iPermissionRepository.findAll(PageRequest.of(page-1,limit));
    }

    public PermissionEntity save(String name, String description) {
        PermissionEntity p =new PermissionEntity();
        p.setName(name);
        p.setDescription(description);
        p.setTime(new Date());
        return iPermissionRepository.save(p);
    }

    @Override
    public PermissionEntity findOne(Integer id) {
        return iPermissionRepository.findById(id).get();
    }

    @Override
    public List<PermissionEntity> findAll() {
        return iPermissionRepository.findAllByDelFalse();
    }

}
