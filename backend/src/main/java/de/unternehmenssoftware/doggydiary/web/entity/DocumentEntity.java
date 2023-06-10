package de.unternehmenssoftware.doggydiary.web.entity;

import de.unternehmenssoftware.doggydiary.web.entity.dto.Document;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity(name = "documents")
@NoArgsConstructor
@Data
public class DocumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private Date date;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @ManyToOne()
    @JoinColumn(name = "fk_id")
    private DogEntity dog;

    public DocumentEntity(Date date, String title, String content, DogEntity dog) {
        this.date = date;
        this.title = title;
        this.content = content;
        this.dog = dog;
    }

    public Document transformToDocument() {
        return new Document(id, date, title, content);
    }
}
