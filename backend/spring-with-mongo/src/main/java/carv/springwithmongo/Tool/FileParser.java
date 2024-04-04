package carv.springwithmongo.Tool;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.Document;
import org.springframework.web.multipart.MultipartFile;

import carv.springwithmongo.Model.GenericRecord;
/*
 * @Author Kyle Plummer
 * @Revision Andrew Carvajal
 * @Version 2.01
 */
public class FileParser {

    /** 
    This will take in a File and read the contents character by character, appending to a string before
     returning the completed string.
     */
    public static String readCompleteChars(MultipartFile file) throws IOException {
        try(InputStreamReader reader = new InputStreamReader(file.getInputStream());){
            StringBuilder builder = new StringBuilder();
            while(reader.ready()) {
                builder.append((char)reader.read());
            }
            
            return builder.toString();
        }catch(IOException e){
            e.printStackTrace();
        }
        return ""; //TODO: Return an Exception Here
        
    }

    /**
     * This reads the entire file at once with readAllBytes.
     * @param file
     * @return
     * @throws IOException
     */
    public static String readAllBytes(File file) throws IOException {
        return new String(Files.readAllBytes(file.toPath())).intern();
    }


    /**
     * This will take a flat file and a spec map in order to create a list of strings, each representing
     * one field value from the flat file.
     * @param data
     * @param spec
     * @return
     * @throws IOException
     */
    public static List<String> readStringFields(String data, Map<String, Field> spec) throws IOException {
        List<String> fieldList = new ArrayList<>();

        Set<String> fields = spec.keySet();
        for(String fieldName : fields) {
            Field field = spec.get(fieldName);
            String fieldValue = data.substring(field.getStartPos(), field.getEndPos()+1).trim();
            fieldList.add(fieldValue);
            System.out.println("[" + fieldName + "][" + fieldValue + "]");
        }
        return fieldList;
    }
    
    static int getRecordSize(Map<String,Field> spec){
        int recordSize = 0;
        
        Collection<Field> fields = spec.values();
        for(Field field: fields){
            recordSize += ((field.getEndPos()+1)-field.getStartPos());
        }

        return recordSize;
    }

    public static List<GenericRecord> readMultiRecord(String data, Map<String, Field> spec) throws IOException {
        List<GenericRecord> fieldList = new ArrayList<>();
        int dataLen = data.length();
        int entry = 0;
        int recordSize = FileParser.getRecordSize(spec);
        while(recordSize*(entry) < dataLen){
            Set<String> fields = spec.keySet();
            Document doc = new Document();
            for(String fieldName: fields){
                Field field = spec.get(fieldName);
                String fieldValue = data.substring(field.getStartPos()+(recordSize*entry), field.getEndPos()+1+(recordSize*entry)).trim();
                doc.append(fieldName, fieldValue);
            }
            fieldList.add(new GenericRecord(doc));
            entry++;
        }
        
        return fieldList;
    }



}
