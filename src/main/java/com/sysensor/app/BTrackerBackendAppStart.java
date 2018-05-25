package com.sysensor.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BTrackerBackendAppStart {
    Logger LOG = LoggerFactory.getLogger(this.getClass());


    public static void main(String[] args) {
        //ApplicationContext context = SpringApplication.run(BTrackerBackendAppStart.class, args);
        //System.out.print(context.toString());
        SpringApplication.run(BTrackerBackendAppStart.class, args);
    }
}
