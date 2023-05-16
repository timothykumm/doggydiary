package de.unternehmenssoftware.doggydiary.web.entity.dto;

import de.unternehmenssoftware.doggydiary.web.entity.dao.DocumentEntity;
import de.unternehmenssoftware.doggydiary.web.entity.dao.DogEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Document {

    private String title;
    private String content;

    public Document(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public DocumentEntity transformToDocumentEntity(DogEntity dogEntity) {
        return new DocumentEntity(title, content, dogEntity);
    }

}
