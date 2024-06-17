package bg.mck.userqueryservice.application.repository;

import bg.mck.userqueryservice.application.entity.UserQueryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserQueryRepository extends MongoRepository<UserQueryEntity, Long> {

}
