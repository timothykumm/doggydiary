package de.unternehmenssoftware.doggydiary.web.entity.dao;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "dogs")
public class DogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "breed")
    private String breed;
    @Column(name = "age")
    private int age;

    @ManyToOne()
    @JoinColumn(name = "fk_email")
    private UserEntity user;

    public DogEntity() {}

    public DogEntity(String email, String name, String breed, int age) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.age = age;
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
}

