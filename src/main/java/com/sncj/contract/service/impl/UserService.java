package com.sncj.contract.service.impl;

import com.sncj.contract.entity.PermissionEntity;
import com.sncj.contract.entity.RoleEntity;
import com.sncj.contract.entity.UserEntity;
import com.sncj.contract.repository.IRoleRepository;
import com.sncj.contract.repository.IUserRepository;
import com.sncj.contract.service.IRoleService;
import com.sncj.contract.service.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Danny on 2018/8/21.
 */
@Service
public class UserService implements IUserService, UserDetailsService {
    @Resource
    private IUserRepository iUserRepository;
    @Resource
    private IRoleService iRoleService;

    @Override
    public UserEntity loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity user = iUserRepository.findByUsername(s);
        if (user != null) {
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            for (RoleEntity role : user.getRole()) {
                if (role != null && role.getName() != null) {
                    for (PermissionEntity permission:role.getPermission()){
                        if (permission != null && permission.getName() != null) {
                            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
                            grantedAuthorities.add(grantedAuthority);
                        }
                    }
                }
            }
            return new UserEntity(user.getId(), user.getUsername(), user.getPassword(), user.getFullname(),grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("admin: " + s + " do not exist!");
        }
    }

    public UserEntity save(String username, String password,String fullname) {

        UserEntity u = iUserRepository.findByUsername(username);
        if(u==null) {
            u=new UserEntity();
            u.setUsername(username);
            u.setPassword(new BCryptPasswordEncoder().encode(password));
            u.setFullname(fullname);
            u.setTime(new Date());
            u.setExist(false);
        }else{
            u.setExist(true);
        }
        return iUserRepository.save(u);
    }

    @Override
    public Page<UserEntity> page(Integer page, Integer limit) {
        return iUserRepository.findAll(PageRequest.of(page-1,limit));
    }

    @Override
    public UserEntity findOne(Integer id) {
        return iUserRepository.findById(id).get();
    }

    @Override
    public UserEntity enabled(Integer userId, Integer roleId, boolean grant) {
        UserEntity u = iUserRepository.findById(userId).get();
        RoleEntity r = iRoleService.findOne(roleId);
        if (grant){
            u.getRole().add(r);
        }else{
            u.getRole().remove(r);
        }

        return iUserRepository.save(u);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return iUserRepository.findByUsername(username);
    }


}
