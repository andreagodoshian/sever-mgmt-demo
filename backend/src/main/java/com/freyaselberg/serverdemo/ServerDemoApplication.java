package com.freyaselberg.serverdemo;

import com.freyaselberg.serverdemo.model.Server;
import com.freyaselberg.serverdemo.model.Status;
import com.freyaselberg.serverdemo.repo.ServerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServerDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerDemoApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ServerRepo serverRepo) {
		return args -> {
			serverRepo.save(new Server
					(null, "192.168.1.160", "Ubuntu Linux",
							"16 GB", "Personal PC",
							"http://localhost:8080/server/image/basicServer.png",
							Status.SERVER_UP));
			serverRepo.save(new Server
					(null, "8.8.8.8", "Google",
							"Unknown", "Test",
							"http://localhost:8080/server/image/basicServer2.png",
							Status.SERVER_UP));
			serverRepo.save(new Server
					(null, "63.135.85.205", "Myspace",
							"Top 8", "Test",
							"http://localhost:8080/server/image/basicServer3.png",
							Status.SERVER_UP));
			serverRepo.save(new Server
					(null, "192.168.1.14", "Kali Linux",
							"32 GB", "School PC",
							"http://localhost:8080/server/image/basicServer4.png",
							Status.SERVER_UP));
		};
	}

}
