package com.example.sweater.domain;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "usr")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private boolean active;

    //    fetch - говорит как данное значение будет подгружаться относительно данной сущности, в случае жадной подгрузки роли будут подгружаться совместно с
    // с загрузкой пользователя, в случае с ленивой загрузкой, роли будут подгружаться только когда пользователь реально обратится к этому полю
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    //  @CollectionTable говорит что данное поле будет храниться в отдельной таблице для которой мы не описывали маппинг
    //@JoinColumn говорит что таблица user_role будет соединяться с текущей таблицей через user_id
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    // Говорим что хотим enum хранить в виде строки
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public boolean isAdmin() {
        return roles.contains(Role.ADMIN);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
