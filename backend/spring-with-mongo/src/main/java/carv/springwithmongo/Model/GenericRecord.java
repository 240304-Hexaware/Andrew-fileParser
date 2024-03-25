package carv.springwithmongo.Model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;


@Document("records")
public class GenericRecord{
    @Id
    ObjectId id;

    @Field
    org.bson.Document document;

    @DocumentReference
    RecordMetadata metadata;

    

    public GenericRecord(){}

    public GenericRecord(org.bson.Document document){
        this.document = document;
    }

    public ObjectId getId() {
        return this.id;
    }
    public void setId(ObjectId id) {
        this.id = id;
    }

    public org.bson.Document getDocument() {
        return this.document;
    }
    public void setDocument(org.bson.Document document) {
        this.document = document;
    }
    
    public RecordMetadata getMetadata() {
        return this.metadata;
    }
    public void setMetadata(RecordMetadata metadata) {
        this.metadata = metadata;
    }

}
