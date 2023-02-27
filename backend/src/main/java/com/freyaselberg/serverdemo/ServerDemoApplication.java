package com.freyaselberg.serverdemo;

import com.freyaselberg.serverdemo.model.Server;
import com.freyaselberg.serverdemo.model.Status;
import com.freyaselberg.serverdemo.repo.ServerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

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

	@Bean // this is a somewhat standard Cors Filter
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();

		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Jwt-Token", "Authorization", "Access-Control-Allow-Credentials", "Filename"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));

		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);

		return new CorsFilter(urlBasedCorsConfigurationSource);
	}

}