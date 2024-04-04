package carv.springwithmongo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import carv.springwithmongo.Controller.*;
import carv.springwithmongo.Service.*;
import carv.springwithmongo.Repository.*;


//import carv.springwithmongo.SpringWithMongoApplication;



@SpringBootTest
class SpringWithMongoApplicationTests {

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

	@Test
	void contextLoads() {
	}

	/*@Test
	public void getAssociateBean(){
		Associate bean = applicationContext.getBean(Associate.class);
		Assertions.assertNotNull(bean);
	}*/

	@Test
	public void getDefaultControllerBean(){
		DefaultController bean = applicationContext.getBean(DefaultController.class);
		Assertions.assertNotNull(bean);
	}
	@Test
	public void getAssociatesControllerBean(){
		AssociatesController bean = applicationContext.getBean(AssociatesController.class);
		Assertions.assertNotNull(bean);
	}

	@Test
	public void getAssociateServiceBean(){
		AssociateService bean = applicationContext.getBean(AssociateService.class);
		Assertions.assertNotNull(bean);
	}

	@Test
	public void getAssociateRepositoryBean(){
		AssociateRepository bean = applicationContext.getBean(AssociateRepository.class);
		Assertions.assertNotNull(bean);
	}


	//Make Mock Request to findAll validating. Mockmvc is later.

	@Test
    public void canPing() throws IOException, InterruptedException {
        HttpClient webClient = HttpClient.newHttpClient();
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/ping")) //TODO: What makes a post and get request?
                .build();
        HttpResponse<String> response = webClient.send(getRequest, HttpResponse.BodyHandlers.ofString()); //TODO: Research second param
        int status = response.statusCode();
        Assertions.assertEquals(200, status);
        String body = response.body();
        Assertions.assertEquals("pong", body);
    }

}
