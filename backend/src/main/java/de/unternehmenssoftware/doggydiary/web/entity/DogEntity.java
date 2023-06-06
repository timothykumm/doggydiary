package de.unternehmenssoftware.doggydiary.web.entity;

import de.unternehmenssoftware.doggydiary.web.entity.dto.Dog;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "dogs")
@NoArgsConstructor
@Data
public class DogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "breed")
    private String breed;
    @Column(name = "age")
    private int age;
    @Column(name = "img")
    @Nullable
    private String img;

    @OneToMany(mappedBy = "dog")
    private List<DocumentEntity> documents;

    @ManyToOne()
    @JoinColumn(name = "fk_email")
    private UserEntity user;

    public DogEntity(String name, String breed, int age, String img, UserEntity user) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.img = img;
        this.user = user;
    }

    public Dog transformToDog() {
        return new Dog(id, name, breed, age, img);
    }
}

