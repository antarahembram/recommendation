package com.stackroute.recommendationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EntityScan("com.stackroute.recommendationservice.model")
@EnableNeo4jRepositories("com.stackroute.recommendationservice.repository")
public class RecommendationserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecommendationserviceApplication.class, args);
	}

}
