package de.unternehmenssoftware.doggydiary.web.entity.dto;

import de.unternehmenssoftware.doggydiary.web.entity.DocumentEntity;
import de.unternehmenssoftware.doggydiary.web.entity.DogEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Document {

    private Long id;
    private String title;
    private String content;

    public Document(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Document(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public DocumentEntity transformToDocumentEntity(DogEntity dogEntity) {
        return new DocumentEntity(title, content, dogEntity);
    }

}
