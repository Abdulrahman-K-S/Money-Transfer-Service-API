FROM openjdk:21

COPY target/MoneyTransactionService-0.0.1-SNAPSHOT.jar app/moneytransactionservice.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app/moneytransactionservice.jar"]