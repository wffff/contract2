package com.sncj.contract.repository;

import com.sncj.contract.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Danny on 2018/8/21.
 */
public interface IUserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByUsername(String username);
}
