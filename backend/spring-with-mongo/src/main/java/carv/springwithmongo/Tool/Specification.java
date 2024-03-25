package carv.springwithmongo.Tool;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

public class Specification {
    /**
     * Here is an example of parsing a JSON file into Java objects. Here we create a map
     * of Tokens which represent fields in a fixed-length file with a name, start position, and end position.
     * Data types are also included here. These tokens can then be used to parse the fixed-length file.
     * @param specFile - Specification JSON file
     * @return Map of tokens
     * @throws IOException
     */
    /*public static Map<String, Field> parseSpec(File specFile) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Field> map = mapper.readValue(specFile, new TypeReference<Map<String, Field>>() {});
        Set<String> keySet = map.keySet();
        for(String s : keySet) {
            map.get(s).setName(s);
        }
        
        System.out.println(map);
        return map;
    }*/
    public static Map<String, Field> parseSpec(File specFile) throws IOException {
        //String specJson = FileParser.readCompleteChars(specFile);
        String specJson = FileParser.readAllBytes(specFile);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Field> map = mapper.readValue(specJson, new TypeReference<Map<String, Field>>() {});
        Set<String> keySet = map.keySet();
        for(String s : keySet) {
            map.get(s).setName(s);
        }
        
        System.out.println("map:"+map);
        return map;
    }

}
