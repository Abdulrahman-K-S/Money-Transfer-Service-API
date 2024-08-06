package com.banquemisr.moneytransactionservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MoneyTransferServiceAPIConfiguration {
    @Bean
    public OpenAPI MoneyTransferServiceAPI() {
        Contact contact = new Contact();
        contact.setName("Abdulrahman Khaled & Mohamed ElSawy");
        contact.setEmail("AK-Salah@outlook.com & M.elsawy2003@gmail.com");

        Info information = new Info().title("Transaction Service API")
                .version("1.0")
                .description("This site contains all the necessary data & information regarding the APIs for the transaction service.")
                .contact(contact);
        return new OpenAPI().info(information);
    }
}
