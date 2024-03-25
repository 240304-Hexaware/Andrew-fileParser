package carv.springwithmongo.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

import org.springframework.http.HttpStatus;

import carv.springwithmongo.Exceptions.AuthenticationFailed;
import carv.springwithmongo.Model.User;
import carv.springwithmongo.Service.UserService;


@RequestMapping("users")
@RestController
@CrossOrigin(origins = "*")
public class UsersController {

    private UserService userService;

    public UsersController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("register") //TODO: Must return User with id attatched
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
    @CrossOrigin("*")
    public User loginUser(@RequestBody User user) throws AuthenticationFailed{
        return userService.login(user);
    }

    @ExceptionHandler(AuthenticationFailed.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String authenticationFailed(AuthenticationFailed e){
        return e.getMessage();
    }
    
}
