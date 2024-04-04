package carv.springwithmongo.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class DefaultController {

    @GetMapping("ping")
    public String getMethodName() {
        return "pong";
    }
    
    
}
