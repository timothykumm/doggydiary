package de.unternehmenssoftware.doggydiary.web.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserCredentials extends UserEntity implements UserDetails {

    public CustomUserCredentials(UserEntity userEntity) {
        super(userEntity.getEmail(), userEntity.getForename(), userEntity.getSurname(), userEntity.getPassword(), userEntity.getOpenai());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<AuthRole> enumList = Arrays.stream(AuthRole.values()).toList();
        return enumList.stream().map(e -> new SimpleGrantedAuthority(e.name())).collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return super.getEmail();
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
        return true;
    }

}
