FROM maven:3.8.2-jdk-8
WORKDIR /car_scan_app
COPY . .
RUN mvn clean install -DskipTests
CMD mvn spring-boot:run
