package bg.mck.userqueryservice.application.repository;

import bg.mck.userqueryservice.application.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity, String> {


}
