FROM openjdk:11
COPY /target/listing-service*.jar /home/app/listing-service/app.jar
EXPOSE 8081
ENTRYPOINT java -jar /home/app/listing-service/app.jar