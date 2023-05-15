package de.unternehmenssoftware.doggydiary.web.entity.dto;

import de.unternehmenssoftware.doggydiary.web.entity.dao.UserEntity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Dog {

    private String name;
    private String breed;
    private int age;
    private UserEntity user;

    public Dog(String name, String breed, int age, UserEntity user) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
