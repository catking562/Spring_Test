package taewookim.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import taewookim.demo.datas.UserData;

@SpringBootApplication
public class DemoApplication {

	public static int taewookimid;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
