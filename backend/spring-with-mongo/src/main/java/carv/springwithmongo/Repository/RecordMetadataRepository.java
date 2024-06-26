package carv.springwithmongo.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import carv.springwithmongo.Model.RecordMetadata;

@Repository
public interface RecordMetadataRepository extends MongoRepository<RecordMetadata, ObjectId> {
    
}
