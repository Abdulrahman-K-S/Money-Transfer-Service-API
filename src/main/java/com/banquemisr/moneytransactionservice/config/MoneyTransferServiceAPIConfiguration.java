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
        Contact developer1 = new Contact();
        developer1.setName("Abdulrahman Khaled");
        developer1.setEmail("AK-Salah@outlook.com");

        Contact developer2 = new Contact();
        developer2.setName("Abdulrahman Khaled");
        developer2.setEmail("AK-Salah@outlook.com");

        Info information = new Info().title("Transaction Service API")
                .version("1.0")
                .description("This site contains all the nessasary data & information regarding the APIs for the transaction service.")
                .contact(developer1)
                .contact(developer2);
        return new OpenAPI().info(information);
    }
}
