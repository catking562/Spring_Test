package taewookim.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import taewookim.demo.datas.UserData;

@SpringBootApplication
public class DemoApplication {

	public static int taewookimid;

	public static void main(String[] args) {
		DataManager.createUser();
		taewookimid = 0;
		UserData userData = DataManager.getUserData(taewookimid);
		userData.setAge(20);
		userData.setPW("i can't write this");
		userData.setNickName("김태우");
		userData.setEmail("taewookim562@gmail.com");
		SpringApplication.run(DemoApplication.class, args);
	}

}
