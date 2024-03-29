package carv.springwithmongo.Model;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import carv.springwithmongo.Model.GenericRecord;

@Document("sessions")
public class Session {

    @Id
    ObjectId id;

    @Field
    private int numDownloaded;

    @DocumentReference
    private List<GenericRecord> records;

    @DocumentReference
    private RecordMetadata rmetadata;

    public Session(){this.records = new ArrayList<GenericRecord>();}

    public Session(RecordMetadata rmetadata){
        this.records = new ArrayList<GenericRecord>();
        this.rmetadata = rmetadata;
    }

    public ObjectId getId() {
        return this.id;
    }
    public void setId(ObjectId id) {
        this.id = id;
    }

    public int getNumDownloaded() {
        return this.numDownloaded;
    }
    public void setNumDownloaded(int numDownloaded) {
        this.numDownloaded = numDownloaded;
    }
    
    public List<GenericRecord> getRecords() {
        return this.records;
    }
    public void setRecords(List<GenericRecord> records) {
        this.records = records;
    }
    public void addRecords(GenericRecord record){
        this.records.add(record);
    }

    public RecordMetadata getRmetadata() {
        return this.rmetadata;
    }
    public void setRmetadata(RecordMetadata rmetadata) {
        this.rmetadata = rmetadata;
    }

}
