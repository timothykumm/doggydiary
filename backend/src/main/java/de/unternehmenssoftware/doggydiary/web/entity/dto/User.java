package de.unternehmenssoftware.doggydiary.web.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.unternehmenssoftware.doggydiary.web.entity.AuthRole;
import de.unternehmenssoftware.doggydiary.web.entity.DogEntity;
import de.unternehmenssoftware.doggydiary.web.entity.UserEntity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class User {

    private String email;
    private String forename;
    private String surname;

    private String openai;

    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private AuthRole authRole;

    public User(String email, String forename, String surname, String openai) {
        this.email = email;
        this.forename = forename;
        this.surname = surname;
        this.openai = openai;
        authRole = AuthRole.USER;
    }

    public UserEntity transformToUserEntity(String password) {
        return new UserEntity(email, forename, surname, openai, password);
    }

    public UserEntity transformToUserEntity(String password, List<DogEntity> dogs) {
        return new UserEntity(email, forename, surname, password, openai, dogs);
    }

}
