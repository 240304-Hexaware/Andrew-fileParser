package carv.springwithmongo;

import org.bson.types.ObjectId;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@SpringBootApplication(scanBasePackages = {
	"carv.springwithmongo.Controller",
	"carv.springwithmongo.Service",
	"carv.springwithmongo.Repository"},
	exclude = {DataSourceAutoConfiguration.class}
)
public class SpringWithMongoApplication {
 
	@Bean
	public Jackson2ObjectMapperBuilderCustomizer customizer(){
		return builder -> builder.serializerByType(ObjectId.class, new ToStringSerializer());
	}
	public static void main(String[] args) {
		
		SpringApplication.run(SpringWithMongoApplication.class, args);
		
	}

}
