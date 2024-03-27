package carv.springwithmongo.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import carv.springwithmongo.Exceptions.AuthenticationFailed;
import carv.springwithmongo.Model.GenericRecord;
import carv.springwithmongo.Model.RecordMetadata;
import carv.springwithmongo.Model.User;
import carv.springwithmongo.Service.FileParserService;
import carv.springwithmongo.Service.RecordMetadataService;
import carv.springwithmongo.Service.UserService;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;



@RestController
public class FileParserController {

    private UserService userService;
    private FileParserService fileParserService;
    private RecordMetadataService rmetadataService;

    public FileParserController(UserService userService, FileParserService fileParserService, RecordMetadataService rmetadataService){
        this.userService = userService;
        this.fileParserService = fileParserService;
        this.rmetadataService = rmetadataService;
    }

    //We're ditching working with html for this, lets continue using postman
    @CrossOrigin(origins="*")
    @PostMapping("file-parser")
    public User postMethodName(@RequestParam("flatfile") MultipartFile flatfile , 
        @RequestParam("specfile") MultipartFile specfile, @RequestHeader("id") ObjectId id) 
        throws IOException, AuthenticationFailed {
        
        //TODO: pass paths as header data
        System.out.println("Recieved Header:"+id);
        if(!userService.authorizeUser(id)){
            throw new AuthenticationFailed();
        }

        RecordMetadata rmetadata = rmetadataService.toBlockStorage(flatfile, specfile, userService.getById(id).getUsername());
        
        List<GenericRecord> persistedRecords = fileParserService.interpretDocument(rmetadata);

        System.out.println("PersistedRecords");
        for(GenericRecord record: persistedRecords){
            //System.out.println(record);
            System.out.println(record.getId());
        }

        return userService.addDocument(id, persistedRecords);
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
