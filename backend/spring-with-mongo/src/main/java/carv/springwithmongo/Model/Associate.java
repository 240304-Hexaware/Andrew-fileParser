package carv.springwithmongo.Model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

//import com.fasterxml.jackson.databind.annotation.JsonSerialize;
//import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;



@Document("associates")
public class Associate {

    @Field("_id")
    private ObjectId id;
    @Field
    private String firstName;
    @Field
    private String lastName;

    public Associate(){}

    public Associate(ObjectId id, String firstName, String lastName){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public ObjectId getId() {
        return this.id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    //Now the hashcode needs to be updated, not enough experience
    /*public String toString(){
        StringBuilder builder = new StringBuilder();

        builder.append("_id: "); builder.append(this.id);
        builder.append("\nfirstName: "); builder.append(this.firstName);
        builder.append("\nlastName: "); builder.append(this.lastName);

        return builder.toString();
    }*/

}
