package mang.church;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Mang Church API",
				version = "1.0",
				description = "API responsible for Mang Church",
				contact = @Contact(
						name = "develop team",
						email = "gdavi989@gmail.com"
				)
		)
)
public class MangChurchApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MangChurchApiApplication.class, args);
	}

}
