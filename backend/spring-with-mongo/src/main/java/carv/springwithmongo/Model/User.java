package carv.springwithmongo.Model;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("users")
public class User {

    @Id
    private ObjectId id;
    @Field
    private String username;
    @Field
    private String password;
    @DocumentReference
    private List<GenericRecord> records; //TODO: records are being dumped, not added onto?

    public User(){this.records = new ArrayList<GenericRecord>();}

    public User(ObjectId id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
        this.records = new ArrayList<GenericRecord>();
    }

    public ObjectId getId() {
        return this.id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<GenericRecord> getRecords() {
        return this.records;
    }

    public void setRecords(List<GenericRecord> records) {
        this.records = records;
    }
    public void addRecord(GenericRecord record){
        this.records.add(record);
    }

    public String toString(){
        String content = "";

        content += "_id: " + this.id
            + "\nusername" + this.username
            + "\npassowrd" + this.password;

        return content;
    }

}
