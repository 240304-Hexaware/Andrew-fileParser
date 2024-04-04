package carv.springwithmongo.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import carv.springwithmongo.Service.AssociateService;
import carv.springwithmongo.Model.Associate;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;


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
