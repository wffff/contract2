package com.sncj.contract.service.impl;

import com.sncj.contract.entity.UserEntity;
import com.sncj.contract.repository.IUserRepository;
import com.sncj.contract.service.IUserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Danny on 2018/8/21.
 */
@Service
public class UserService implements IUserService, UserDetailsService {
    @Resource
    private IUserRepository iUserRepository;

    @Override
    public UserEntity loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity user = iUserRepository.findByUsername(s);
        if (user != null) {
            Set<GrantedAuthority> grantedAuthorities = new HashSet();
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getRole());
            grantedAuthorities.add(grantedAuthority);
            return new UserEntity(user.getId(), user.getUsername(), user.getPassword(), user.getFullname(),grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("admin: " + s + " do not exist!");
        }
    }
}
