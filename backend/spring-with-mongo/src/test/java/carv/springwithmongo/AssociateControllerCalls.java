package carv.springwithmongo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import carv.springwithmongo.Model.Associate;

@SpringBootTest
public class AssociateControllerCalls {
    ApplicationContext applicationContext;

	@BeforeEach
	public void build(){
		String[] args = new String[] {};
		applicationContext = SpringApplication.run(SpringWithMongoApplication.class, args);
	}

	@AfterEach
	public void destroy(){
		SpringApplication.exit(applicationContext);
	}

    //@Test
    public void getAllAssociates() throws IOException, InterruptedException{
        HttpClient webClient = HttpClient.newHttpClient();
        HttpRequest getRequest = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8080/associates/getAll"))
            .build();
        //Build the response for a List<Associates> Not sure If I'm gonna need abstraction to solve this
        HttpResponse<String> response = webClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        ObjectMapper om = new ObjectMapper();
        System.out.println("Type of Response Body: " + response.body().getClass());
        List<Associate> allAssociates = om.readValue(response.body(), new TypeReference<List<Associate>>(){});
        int status = response.statusCode();
        Assertions.assertEquals(200, status);
        System.out.println("Printing all Associates: ");
        for(Associate a: allAssociates){
            System.out.print(a);
        }
        
    }
}
