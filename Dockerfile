FROM openjdk:17-jdk-slim

WORKDIR /app

COPY Hola.java .

RUN javac Hola.java

EXPOSE 8082

CMD ["java", "Hola"]
