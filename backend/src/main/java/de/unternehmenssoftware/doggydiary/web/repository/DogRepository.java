package de.unternehmenssoftware.doggydiary.web.repository;

import de.unternehmenssoftware.doggydiary.web.entity.DogEntity;
import de.unternehmenssoftware.doggydiary.web.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DogRepository extends CrudRepository<DogEntity, Long> {
    List<DogEntity> getAllByUser(UserEntity user);
    Optional<DogEntity> getDogEntityByIdAndUser(Long dogId, UserEntity user);

}
