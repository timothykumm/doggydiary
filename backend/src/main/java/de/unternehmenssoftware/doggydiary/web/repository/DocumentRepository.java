package de.unternehmenssoftware.doggydiary.web.repository;

import de.unternehmenssoftware.doggydiary.web.entity.DocumentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends CrudRepository<DocumentEntity, Long> {

    /*@Query(value = "SELECT doc.id, doc.content, doc.title from documents as doc " +
            "inner join dogs as dog on dog.id = doc.fk_id " +
            "inner join users as us on dog.fk_email = us.email " +
            "where us.email = :email AND dog.id = :dogId", nativeQuery = true)*/
    @Query(value = "SELECT doc from documents as doc " +
                    "join dogs as dog on dog.id = doc.dog.id " +
                    "join users as us on dog.user.email = us.email " +
                    "where us.email = :email AND dog.id = :dogId")
    List<DocumentEntity> getDocumentsByUserAndDogId(@Param("email") String email, @Param("dogId") String dogId);

}
