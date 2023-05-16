package de.unternehmenssoftware.doggydiary.web.entity.dao;

import de.unternehmenssoftware.doggydiary.web.entity.dto.Document;
import de.unternehmenssoftware.doggydiary.web.entity.dto.Dog;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "documents")
@NoArgsConstructor
@Getter
@Setter
public class DocumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @ManyToOne()
    @JoinColumn(name = "fk_id")
    private DogEntity dog;

    public DocumentEntity(String title, String content, DogEntity dog) {
        this.title = title;
        this.content = content;
        this.dog = dog;
    }

    public Document transformToDocument() {
        return new Document(title, content);
    }
}
