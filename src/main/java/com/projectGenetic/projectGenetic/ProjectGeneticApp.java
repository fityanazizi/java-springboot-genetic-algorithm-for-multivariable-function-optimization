package com.projectGenetic.projectGenetic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class ProjectGeneticApp {

	public static void main(String[] args) {
		SpringApplication.run(ProjectGeneticApp.class, args);
	}
}

