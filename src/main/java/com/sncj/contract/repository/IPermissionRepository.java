package com.sncj.contract.repository;

import com.sncj.contract.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPermissionRepository extends JpaRepository<PermissionEntity,Integer> {
    List<PermissionEntity> findAllByDelFalse();
}
