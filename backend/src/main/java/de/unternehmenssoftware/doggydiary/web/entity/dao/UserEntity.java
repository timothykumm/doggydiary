package de.unternehmenssoftware.doggydiary.web.entity.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity(name = "users")
public class UserEntity {

    @Id
    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user")
    private List<DogEntity> dogs;

    public UserEntity() {}

    public UserEntity(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserEntity(String email, String password, List<DogEntity> dogs) {
        this.email = email;
        this.password = password;
        this.dogs = dogs;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
