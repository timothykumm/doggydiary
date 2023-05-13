package de.unternehmenssoftware.doggydiary.web.entity.dto;

import de.unternehmenssoftware.doggydiary.web.entity.dao.DogEntity;

import java.util.List;

public class User {

    private String email;
    private String password;
    private List<DogEntity> dogs;

    public User(String email, String password, List<DogEntity> dogs) {
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
