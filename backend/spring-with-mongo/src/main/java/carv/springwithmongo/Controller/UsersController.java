package carv.springwithmongo.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import org.springframework.http.HttpStatus;

import carv.springwithmongo.Exceptions.AuthenticationFailed;
import carv.springwithmongo.Model.RecordMetadata;
import carv.springwithmongo.Model.Session;
import carv.springwithmongo.Model.User;
import carv.springwithmongo.Service.SessionService;
import carv.springwithmongo.Service.UserService;


@RequestMapping("users")
@RestController
@CrossOrigin(origins = "*")
public class UsersController {

    private UserService userService;
    private SessionService sessionService;

    public UsersController(UserService userService,
                           SessionService sessionService
        ){
            this.userService = userService;
            this.sessionService = sessionService;
        }

    @PostMapping("register")
    @ResponseStatus(HttpStatus.OK)
    public User registerUser(@RequestBody User user) throws AuthenticationFailed{
        User checkUser = userService.register(user);

        //Can we throw this in a service somewhere?
        String blockStorage = "--blockStorage";
        String userBlockStoragePath = "..\\blockStorage\\" + checkUser.getUsername() + blockStorage + "\\";
        File userFolder = new File(userBlockStoragePath);
        if(!userFolder.mkdir()){
            throw new AuthenticationFailed();
        }
        File specFiles = new File(userBlockStoragePath + "specFiles");
        File flatFiles = new File(userBlockStoragePath + "flatFiles");
        
        specFiles.mkdir();
        flatFiles.mkdir();
        
        return checkUser;
    }

    @PostMapping("login")
    @ResponseStatus(HttpStatus.OK)
    public User loginUser(@RequestBody User user) throws AuthenticationFailed{
        return userService.login(user);
    }

    @PostMapping("authorize")
    @ResponseStatus(HttpStatus.OK)
    public Boolean authenticateId(@RequestBody User user) throws AuthenticationFailed{
        return userService.authorizeUser(user.getId());
    }

    @GetMapping("{userId}/getFlatFileNames")
    @ResponseStatus(HttpStatus.OK)
    public String[] getFlatFileNames(@PathVariable String userId) throws AuthenticationFailed{
        return this.userService.getAllFlatfileNames(new ObjectId(userId));
    }

    @GetMapping("{userId}/getSpecFileNames")
    @ResponseStatus(HttpStatus.OK)
    public String[] getSpecFileNames(@PathVariable String userId) throws AuthenticationFailed{
        return this.userService.getAllSpecfileNames(new ObjectId(userId));
    }

    @GetMapping("{userId}/loadSpecFilter/{specfileName}")
    @ResponseStatus(HttpStatus.OK)
    public List<Session> filterSessionsBySpec(@PathVariable String userId, @PathVariable String specfileName) throws AuthenticationFailed{
        System.out.println("Filter Hit");
        List<Session> allSessions = this.userService.getById(new ObjectId(userId)).getSessions();
        List<Session> filteredSessions = new ArrayList<Session>();
        for(Session session: allSessions){
            RecordMetadata metadata = session.getRmetadata();
            if(metadata.getSpecfilePath().contains(specfileName)){
                filteredSessions.add(session);
            }
        }
        return filteredSessions;
    }


    @ExceptionHandler(AuthenticationFailed.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String authenticationFailed(AuthenticationFailed e){
        return e.getMessage();
    }
    
}
