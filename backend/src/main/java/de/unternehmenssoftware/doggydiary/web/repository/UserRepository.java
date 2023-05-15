package de.unternehmenssoftware.doggydiary.web.repository;

import de.unternehmenssoftware.doggydiary.web.entity.dao.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {

    UserEntity findByEmail(String email);

}