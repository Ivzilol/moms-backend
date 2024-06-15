package bg.mck.userqueryservice.application.repository;

import bg.mck.userqueryservice.application.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, Long> {

    UserEntity findByEmail(String Email);
}
