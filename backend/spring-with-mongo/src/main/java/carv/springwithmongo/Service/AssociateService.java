package carv.springwithmongo.Service;

import java.util.List;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import carv.springwithmongo.Repository.AssociateRepository;
import carv.springwithmongo.Model.Associate;

@Service
public class AssociateService {
    
    private AssociateRepository associateRepository;

    public AssociateService(AssociateRepository associateRepository){
        this.associateRepository = associateRepository;
    }

    public List<Associate> findAll(){
        return associateRepository.findAll();
    }

}
