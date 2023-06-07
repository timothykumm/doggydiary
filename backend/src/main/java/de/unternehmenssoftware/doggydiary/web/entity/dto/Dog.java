package de.unternehmenssoftware.doggydiary.web.entity.dto;

import de.unternehmenssoftware.doggydiary.web.entity.DogEntity;
import de.unternehmenssoftware.doggydiary.web.entity.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class Dog {

    private Long id;
    private String name;
    private String breed;
    private Date birthdate;
    private String img;

    public Dog(String name, String breed, Date birthdate, String img) {
        this.name = name;
        this.breed = breed;
        this.birthdate = birthdate;
        this.img = img;
    }

    public Dog(Long id, String name, String breed, Date birthdate, String img) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.birthdate = birthdate;
        this.img = img;
    }

    public DogEntity transformToDogEntity(UserEntity userEntity) {
        return new DogEntity(name, breed, birthdate, img, userEntity);
    }

}
