package de.unternehmenssoftware.doggydiary.web.entity.dto;

import de.unternehmenssoftware.doggydiary.web.entity.DocumentEntity;
import de.unternehmenssoftware.doggydiary.web.entity.DogEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class Document {

    private Long id;
    private Date date;
    private String title;
    private String content;

    public Document(Long id, Date date, String title, String content) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.content = content;
    }

    public Document(Date date, String title, String content) {
        this.date = date;
        this.title = title;
        this.content = content;
    }

    public DocumentEntity transformToDocumentEntity(DogEntity dogEntity) {
        return new DocumentEntity(date, title, content, dogEntity);
    }

}
