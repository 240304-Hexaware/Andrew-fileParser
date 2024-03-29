package carv.springwithmongo.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import carv.springwithmongo.Model.RecordMetadata;
import carv.springwithmongo.Repository.RecordMetadataRepository;

@Service
public class RecordMetadataService {

    private RecordMetadataRepository metaRepo;

    public RecordMetadataService(RecordMetadataRepository metaRepo){
        this.metaRepo = metaRepo;
    }

    public RecordMetadata save(RecordMetadata metadata){
        return metaRepo.save(metadata);
    }
    
    public RecordMetadata toBlockStorage(MultipartFile flatFile, MultipartFile specFile, String username) throws IOException{
        String flatfilePath = flatFile.getOriginalFilename();
        String specfilePath = specFile.getOriginalFilename();
        System.out.println("flatfilePath: " + flatfilePath);
        System.out.println("specfilePath: " + specfilePath);

        //TODO: This makes us have state.
        String absolutePath = "C:\\Users\\Macho\\Documents\\VisualStudio_Workspace\\Revature\\RevTraining\\file-parser-project-phase2\\backend\\blockStorage\\";
        String blockStorage = "--blockStorage";
        String flatfileStored = absolutePath+username+blockStorage+"\\flatFiles\\"+flatfilePath;
        String specfileStored = absolutePath+username+blockStorage+"\\specFiles\\"+specfilePath;
        LocalDateTime dateTime = LocalDateTime.now();
        
        //TODO: MultipartFile is being consumed twice, which it can only be consumed once. I need to make a copy somehow.

        flatFile.transferTo(new File(flatfileStored));
        specFile.transferTo(new File(specfileStored));

        // Document metadata = new Document();
        // metadata.append("flatfilePath", flatfileStored);
        // metadata.append("specfilePath", specfileStored);
        // metadata.append("datetime", dateTime);

        RecordMetadata metadata = new RecordMetadata();
        metadata.setFlatfilePath(flatfileStored);
        metadata.setSpecfilePath(specfileStored);
        metadata.setDatetime(dateTime);
        
        return metaRepo.save(metadata);
    }
}
