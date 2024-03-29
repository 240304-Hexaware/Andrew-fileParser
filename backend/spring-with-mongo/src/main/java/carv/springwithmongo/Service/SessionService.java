package carv.springwithmongo.Service;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import carv.springwithmongo.Model.Session;
import carv.springwithmongo.Repository.SessionRepository;

@Service
public class SessionService {
    
    public SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository){
        this.sessionRepository = sessionRepository;
    }

    public Session save(Session session){return this.sessionRepository.save(session);}

    public Session findById(ObjectId id) throws Exception{
        Optional<Session> optional = this.sessionRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        throw new Exception("invalid session find");
    }
}
