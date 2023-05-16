package de.unternehmenssoftware.doggydiary.web.entity.dao;

import de.unternehmenssoftware.doggydiary.web.entity.dto.Dog;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "dogs")
@NoArgsConstructor
@Getter
@Setter
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

    @OneToMany(mappedBy = "dog")
    private List<DocumentEntity> documents;

    @ManyToOne()
    @JoinColumn(name = "fk_email")
    private UserEntity user;

    public DogEntity(String name, String breed, int age, UserEntity user) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.user = user;
    }

    public Dog transformToDog() {
        return new Dog(id, name, breed, age);
    }
}

