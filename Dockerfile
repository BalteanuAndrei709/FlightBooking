FROM openjdk:17

COPY target/PaymentService-0.0.1-SNAPSHOT.jar payment.jar

ENTRYPOINT ["java", "-jar", "payment.jar"]
