package com.sncj.contract.repository;

import com.sncj.contract.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRoleRepository extends JpaRepository<RoleEntity, Integer>{
    List<RoleEntity> findAllByDelFalse();
}
