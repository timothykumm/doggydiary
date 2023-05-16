package de.unternehmenssoftware.doggydiary.web.entity.dto;

import de.unternehmenssoftware.doggydiary.web.entity.dao.AuthRole;
import de.unternehmenssoftware.doggydiary.web.entity.dao.DogEntity;
import de.unternehmenssoftware.doggydiary.web.entity.dao.UserEntity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class User {

    private String email;
    private String forename;
    private String surname;

    @Enumerated(EnumType.STRING)
    private AuthRole authRole;

    public User(String email, String forename, String surname) {
        this.email = email;
        this.forename = forename;
        this.surname = surname;
        authRole = AuthRole.USER;
    }

    public UserEntity transformToUserEntity(String password) {
        return new UserEntity(email, forename, surname, password);
    }

    public UserEntity transformToUserEntity(String password, List<DogEntity> dogs) {
        return new UserEntity(email, forename, surname, password, dogs);
    }

}
