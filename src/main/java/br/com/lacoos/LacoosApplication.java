package br.com.lacoos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LacoosApplication {

    public static void main(String[] args) {
        SpringApplication.run(LacoosApplication.class, args);
        System.out.println("Api Rodando!");
    }

}
