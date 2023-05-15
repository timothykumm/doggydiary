package de.unternehmenssoftware.doggydiary.web.entity.dto;

import de.unternehmenssoftware.doggydiary.web.entity.dao.DogEntity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Document {

    private String title;
    private String content;
    private DogEntity dog;

    public Document(long id, String title, String content, DogEntity dog) {
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
