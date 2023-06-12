package com.onlyabhinav.dbtextract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.onlyabhinav.dbtextract.start.LaunchPad;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class DbtextractApplication {

	
	@Autowired
	private LaunchPad launchPad;
	
	public static void main(String[] args) {
		SpringApplication.run(DbtextractApplication.class, args);
		
	}
	
	
	@PostConstruct
	private void onStart() {
		launchPad.start();
	}

}
