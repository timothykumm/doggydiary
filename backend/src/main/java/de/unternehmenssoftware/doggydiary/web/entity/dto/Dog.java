package de.unternehmenssoftware.doggydiary.web.entity.dto;

import de.unternehmenssoftware.doggydiary.web.entity.DogEntity;
import de.unternehmenssoftware.doggydiary.web.entity.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Dog {

    private Long id;
    private String name;
    private String breed;
    private int age;
    private String img;

    public Dog(String name, String breed, int age, String img) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.img = img;
    }

    public Dog(Long id, String name, String breed, int age, String img) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.img = img;
    }

    public DogEntity transformToDogEntity(UserEntity userEntity) {
        return new DogEntity(name, breed, age, img, userEntity);
    }

}
