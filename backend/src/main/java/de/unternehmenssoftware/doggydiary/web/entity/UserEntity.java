package de.unternehmenssoftware.doggydiary.web.entity;

import de.unternehmenssoftware.doggydiary.web.entity.dto.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity(name = "users")
@NoArgsConstructor
@Data
public class UserEntity {

    @Id
    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "forename", length = 100)
    private String forename;

    @Column(name = "surname", length = 100)
    private String surname;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "openai", length = 51)
    private String openai;

    @OneToMany(mappedBy = "user")
    private List<DogEntity> dogs;

    @Enumerated(EnumType.STRING)
    private AuthRole authRole;

    public UserEntity(String email, String forename, String surname, String password, String openai) {
        this.email = email;
        this.forename = forename;
        this.surname = surname;
        this.password = password;
        this.openai = openai;
        this.authRole = AuthRole.USER;
    }

    public UserEntity(String email, String forename, String surname, String password, String openai, List<DogEntity> dogs) {
        this.email = email;
        this.forename = forename;
        this.surname = surname;
        this.password = password;
        this.openai = openai;
        this.dogs = dogs;
        this.authRole = AuthRole.USER;
    }

    /*public User transformToUser() {
        return new User(email, forename, surname, password,
                dogs.stream().map(DogEntity::transformToDog).collect(Collectors.toList()));
    }*/

    public User transformToUser() {
        return new User(email, forename, surname, openai);
    }

}
