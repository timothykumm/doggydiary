package de.unternehmenssoftware.doggydiary.web.entity.dao;

import jakarta.persistence.*;

@Entity(name = "documents")
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

    public DocumentEntity() {}

    public DocumentEntity(long id, String title, String content, DogEntity dog) {
        this.title = title;
        this.content = content;
        this.dog = dog;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public DogEntity getDog() {
        return dog;
    }

    public void setDog(DogEntity dog) {
        this.dog = dog;
    }
}
