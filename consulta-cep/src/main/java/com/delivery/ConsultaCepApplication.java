package com.delivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Classe principal: fica no pacote com.delivery para o Spring
// encontrar o controller, o model e a config (que estão em subpacotes)
@SpringBootApplication
public class ConsultaCepApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsultaCepApplication.class, args);
    }

}
