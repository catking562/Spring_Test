package taewookim.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import taewookim.demo.data.DataManager;
import taewookim.demo.data.UserData;

import javax.xml.crypto.Data;
import java.io.File;

@SpringBootApplication
public class DemoApplication {

	public static int taewookimid;

	public static void main(String[] args) {
		taewookimid = DataManager.createUser();
		UserData userData = DataManager.getUserData(taewookimid);
		userData.setAge(20);
		userData.setPW("i can't write this");
		userData.setNickName("김태우");
		SpringApplication.run(DemoApplication.class, args);
	}

}
