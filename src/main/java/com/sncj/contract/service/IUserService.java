package com.sncj.contract.service;

import com.sncj.contract.entity.UserEntity;
import org.springframework.data.domain.Page;

/**
 * Created by Danny on 2018/8/21.
 */
public interface IUserService {
    UserEntity save(String username, String password,String fullname);

    Page<UserEntity> page(Integer page, Integer limit);
}
