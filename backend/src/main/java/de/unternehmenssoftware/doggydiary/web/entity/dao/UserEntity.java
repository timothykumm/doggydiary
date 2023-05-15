package de.unternehmenssoftware.doggydiary.web.entity.dao;

import de.unternehmenssoftware.doggydiary.web.entity.dto.User;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "users")
@NoArgsConstructor
public class UserEntity implements UserDetails {

    @Id
    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "forename", length = 100)
    private String forename;

    @Column(name = "surname", length = 100)
    private String surname;

    @Column(name = "password", length = 100)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<DogEntity> dogs;

    @Enumerated(EnumType.STRING)
    private AuthRole authRole;

    public UserEntity(String email, String forename, String surname, String password) {
        this.email = email;
        this.forename = forename;
        this.surname = surname;
        this.password = password;
        this.authRole = AuthRole.USER;
    }

    public UserEntity(String email, String forename, String surname, String password, List<DogEntity> dogs) {
        this.email = email;
        this.forename = forename;
        this.surname = surname;
        this.password = password;
        this.dogs = dogs;
        this.authRole = AuthRole.USER;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<DogEntity> getDogs() {
        return dogs;
    }

    public void setDogs(List<DogEntity> dogs) {
        this.dogs = dogs;
    }

    public User transformToUser() {
        return new User(email, forename, surname, password, dogs);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<AuthRole> enumList = Arrays.stream(AuthRole.values()).toList();
        return enumList.stream().map(e -> new SimpleGrantedAuthority(e.name())).collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
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
