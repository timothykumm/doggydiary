package de.unternehmenssoftware.doggydiary.web.repository;

import de.unternehmenssoftware.doggydiary.web.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {

    Optional<UserEntity> findByEmail(String email);
    boolean existsUserEntityByEmail(String email);

}
