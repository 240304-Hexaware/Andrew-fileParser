package carv.springwithmongo.Repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import carv.springwithmongo.Model.User;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId>{
    Optional<User> findByUsernameAndPassword(String username, String password);
    Optional<User> findByUsername(String username);
    
}
