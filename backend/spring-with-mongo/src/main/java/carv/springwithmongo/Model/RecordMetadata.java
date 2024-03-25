package carv.springwithmongo.Model;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document("rmetadata")
public class RecordMetadata {
    @Id
    ObjectId id;

    @Field
    String flatfilePath;

    @Field
    String specfilePath;

    @Field
    LocalDateTime datetime;


    public RecordMetadata(){}
    

    public ObjectId getId() {
        return this.id;
    }
    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getFlatfilePath() {
        return this.flatfilePath;
    }
    public void setFlatfilePath(String flatfilePath) {
        this.flatfilePath = flatfilePath;
    }

    public String getSpecfilePath() {
        return this.specfilePath;
    }
    public void setSpecfilePath(String specfilePath) {
        this.specfilePath = specfilePath;
    }
    
    public LocalDateTime getDatetime() {
        return this.datetime;
    }
    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }
}
