package belov.vlad.dapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class DappApplication implements WebMvcConfigurer {
	public static void main(String[] args) {
		SpringApplication.run(DappApplication.class, args);
	}
}
