package carv.springwithmongo.Repository;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import carv.springwithmongo.Model.Associate;

@Repository
public interface AssociateRepository extends MongoRepository<Associate, ObjectId>{
    
    //create

    //read

    //update

    //delete
    
}
