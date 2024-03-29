package carv.springwithmongo.Service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import carv.springwithmongo.Exceptions.AuthenticationFailed;
import carv.springwithmongo.Model.GenericRecord;
import carv.springwithmongo.Model.Session;
import carv.springwithmongo.Model.User;
import carv.springwithmongo.Repository.UserRepository;

@Service
public class UserService {
    
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User register(User user) throws AuthenticationFailed{
        Optional<User> duplicate = userRepository.findByUsername(user.getUsername());
        if(!duplicate.isPresent()){
            return userRepository.save(user);
        }
        throw new AuthenticationFailed();
    }

    public User login(User user) throws AuthenticationFailed{ 
        Optional<User> optional = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if(optional.isPresent()){
            return optional.get();
        }
        throw new AuthenticationFailed();
    }

    public boolean authorizeUser(ObjectId id){
        Optional<User> optional = userRepository.findById(id);
        return (optional.isPresent())? true: false;
    }

    public User addSession(ObjectId userId , Session session){//TODO: Change GenericRecord -> Session
        Optional<User> optional = userRepository.findById(userId);
        User user = optional.get();
        user.addSessions(session);
        return userRepository.save(user);
    }

    public User getById(ObjectId id) throws AuthenticationFailed{
        Optional<User> optional = userRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        throw new AuthenticationFailed();
    }
}
