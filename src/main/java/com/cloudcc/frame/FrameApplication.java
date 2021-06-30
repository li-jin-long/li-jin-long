package com.cloudcc.frame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FrameApplication {

    public static void main(String[] args) {
        String a = "100";
       Integer b=  Integer.parseInt(a);
        System.out.println(b);

    }

}
