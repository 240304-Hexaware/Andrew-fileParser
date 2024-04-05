package carv.springwithmongo.Controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import carv.springwithmongo.Exceptions.AuthenticationFailed;
import carv.springwithmongo.Model.GenericRecord;
import carv.springwithmongo.Model.RecordMetadata;
import carv.springwithmongo.Model.Session;
import carv.springwithmongo.Model.User;
import carv.springwithmongo.Service.FileParserService;
import carv.springwithmongo.Service.RecordMetadataService;
import carv.springwithmongo.Service.SessionService;
import carv.springwithmongo.Service.UserService;

import java.io.IOException;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

//mongodb+srv://andrewcarvajal38:xOZDUYwZea38D694@file-parser-cluster0.8kdatr1.mongodb.net/?retryWrites=true&w=majority&appName=file-parser-cluster0
//mongodb+srv://andrewcarvajal38:xOZDUYwZea38D694@file-parser-cluster0.8kdatr1.mongodb.net/file-parser-cluster0
//spring.data.mongodb.uri
@RestController
public class FileParserController {

    private UserService userService;
    private FileParserService fileParserService;
    private RecordMetadataService rmetadataService;
    private SessionService sessionService;

    public FileParserController(UserService userService, 
                                FileParserService fileParserService, 
                                RecordMetadataService rmetadataService,
                                SessionService sessionService
        ){
            this.userService = userService;
            this.fileParserService = fileParserService;
            this.rmetadataService = rmetadataService;
            this.sessionService = sessionService;
        }

    //We're ditching working with html for this, lets continue using postman
    @CrossOrigin(origins="*")
    @PostMapping("file-parser")
    public User postMethodName(@RequestParam("flatfile") MultipartFile flatfile , 
        @RequestParam("specfile") MultipartFile specfile, @RequestHeader("id") ObjectId id) 
        throws IOException, AuthenticationFailed {
        
        //TODO: pass paths as header data
        System.out.println("Recieved file-parse:"+id);
        if(!userService.authorizeUser(id)){
            throw new AuthenticationFailed();
        }

        RecordMetadata rmetadata = rmetadataService.toBlockStorage(flatfile, specfile, userService.getById(id).getUsername());
        
        Session toPSession = fileParserService.interpretDocument(rmetadata);

        Session persistedSession = sessionService.save(toPSession);

        System.out.println("PersistedRecords");
        for(GenericRecord session: persistedSession.getRecords()){ //persisted Sessions
            //System.out.println(record);
            
            System.out.println(session.getId());
        }

        return userService.addSession(id, persistedSession);
    }

    @CrossOrigin(origins="*")
    @GetMapping("getRecordsBySession/{id}")
    public Document[] getRecordsBySessionId(@PathVariable String id) throws Exception{
        //System.out.println("Recieved: " + id);
        Session session = sessionService.findById(new ObjectId(id));
        Document[] docs = new Document[session.getRecords().size()];
        int index = 0;
        for(GenericRecord record: session.getRecords()){
            docs[index++] = record.getDocument();
        }
        return docs;
    }

    @CrossOrigin(origins="*")
    @GetMapping("getMetadataBySession/{id}")
    public RecordMetadata getMetadataBySessionId(@PathVariable String id) throws Exception{
        //System.out.println("Recieved: " + id);
        return sessionService.findById(new ObjectId(id)).getRmetadata();
    }


    @ExceptionHandler(AuthenticationFailed.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String authenticationFailed(AuthenticationFailed e){
        return e.getMessage();
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String fileFail(IOException e){
        return e.getMessage();
    }
    
    
}
