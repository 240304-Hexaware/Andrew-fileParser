package carv.springwithmongo.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import carv.springwithmongo.Model.GenericRecord;
import carv.springwithmongo.Model.RecordMetadata;
import carv.springwithmongo.Model.Session;
import carv.springwithmongo.Repository.FileParserRepository;
import carv.springwithmongo.Tool.Field;
import carv.springwithmongo.Tool.FileParser;
import carv.springwithmongo.Tool.Specification;

@Service
public class FileParserService {
    
    private FileParserRepository fileParserRepository;
    

    public FileParserService(FileParserRepository fileParserRepository){
        this.fileParserRepository = fileParserRepository;
    }

    /* This Implementation is meant to work for reading one flatfile entry
     * 
     */
    public Session interpretDocument(RecordMetadata rmetadata) throws IOException{

        // List<Session> persistedList = new ArrayList<Session>();
        Map<String, Field> map = Specification.parseSpec(new File(rmetadata.getSpecfilePath())); //This'll represent our key
        String data = FileParser.readAllBytes(new File(rmetadata.getFlatfilePath()));
        List<GenericRecord> docList = FileParser.readMultiRecord(data,map);
        Session session = new Session();
        session.setRmetadata(rmetadata);

        for(GenericRecord record: docList){
            session.addRecords(fileParserRepository.save(record));
        }

        return session;
    }

    public void metadata(){

    }

}
