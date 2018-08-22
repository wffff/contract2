package com.sncj.contract.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by Danny on 2018/8/21.
 */
@Entity
@Table(name = "t_user")
public class UserEntity extends BaseEntity implements UserDetails {
    private String role;
    private String username;
    private String password;
    private String fullname;

    @Transient
    private Set<GrantedAuthority> authorities;

    private Boolean enabled=true;
    private Boolean expired=false;
    private Boolean locked=false;
    private Boolean limited=false;

    public UserEntity() {
    }

    public UserEntity(Integer id, String username, String password,String fullname, Set<GrantedAuthority> grantedAuthorities) {
        super.id=id;
        this.username=username;
        this.password=password;
        this.fullname=fullname;
        this.authorities=grantedAuthorities;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }


    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !limited;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Set<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
