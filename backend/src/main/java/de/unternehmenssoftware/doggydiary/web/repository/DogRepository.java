package de.unternehmenssoftware.doggydiary.web.repository;

import de.unternehmenssoftware.doggydiary.web.entity.DogEntity;
import de.unternehmenssoftware.doggydiary.web.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DogRepository extends CrudRepository<DogEntity, Long> {
    List<DogEntity> getAllByUser(UserEntity user);
    Optional<DogEntity> getDogEntityByIdAndUser(Long dogId, UserEntity user);
    @Query(value = "SELECT d.img from dogs as d " +
            "join users as us on d.user.email = us.email " +
            "where us.email = :email")
    List<String> getAllImagesByUser(@Param("email") String email);
}
