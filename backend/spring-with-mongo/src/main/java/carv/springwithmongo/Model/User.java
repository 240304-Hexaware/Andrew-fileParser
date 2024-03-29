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
    private List<Session> sessions; //TODO: records are being dumped, not added onto?

    public User(){this.sessions = new ArrayList<Session>();}

    public User(String username, String password){
        this.id = new ObjectId("");
        this.username = username;
        this.password = password;
    }

    public User(ObjectId id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
        this.sessions = new ArrayList<Session>();
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

    public List<Session> getSessions() {
        return this.sessions;
    }

    public void setSessions(List<Session> session) {
        this.sessions = session;
    }
    public void addSessions(Session session){
        this.sessions.add(session);
    }

    public String toString(){
        String content = "";

        content += "_id: " + this.id
            + "\nusername" + this.username
            + "\npassowrd" + this.password;

        return content;
    }

}
