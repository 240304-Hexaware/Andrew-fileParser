package carv.springwithmongo.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import carv.springwithmongo.Service.AssociateService;
import carv.springwithmongo.Model.Associate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("associates")
@RestController
public class AssociatesController {

    private AssociateService associateService;

    public AssociatesController(AssociateService associateService){
        this.associateService = associateService;
    }

    @GetMapping("getAll")
    public List<Associate> getAll(){
        return associateService.findAll();
    }
    
    
    


}
