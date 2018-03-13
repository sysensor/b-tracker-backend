package com.sysensor.app;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BTrackerBackendAppStart {
	Logger LOG = Logger.getLogger(this.getClass());


	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(BTrackerBackendAppStart.class, args);
		System.out.print(context.toString());
	}
}
