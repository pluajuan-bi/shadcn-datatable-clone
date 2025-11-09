FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY Hola.java .

RUN javac Hola.java

EXPOSE 8082
CMD ["java", "Hola"]
