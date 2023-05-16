package de.unternehmenssoftware.doggydiary.web.entity.dto;

import de.unternehmenssoftware.doggydiary.web.entity.dao.DogEntity;
import de.unternehmenssoftware.doggydiary.web.entity.dao.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Dog {

    private Long id;
    private String name;
    private String breed;
    private int age;

    public Dog(Long id, String name, String breed, int age) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.age = age;
    }

    public DogEntity transformToDogEntity(UserEntity userEntity) {
        return new DogEntity(name, breed, age, userEntity);
    }

}
