package carv.springwithmongo.Repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import carv.springwithmongo.Model.Associate;

@Repository
public interface AssociateRepository extends MongoRepository<Associate, ObjectId>{
    
    //create

    //read
    //List<Associate> findAllById(ObjectId id);

    //update

    //delete
    
}
