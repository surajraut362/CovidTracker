package com.covid19;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sun.tools.javac.Main;

@SpringBootApplication
public class Covid19TrackerApp {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
