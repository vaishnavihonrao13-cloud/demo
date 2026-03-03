This is a simple Todo application using Spring Boot,PostgreSQL,React



1. To set Database 

1\. Install PostgreSQL.

2\. Open PostgreSQL and run:

sql

CREATE DATABASE todo\_db;

3.To  Configure `application.properties` for backend

properties

spring.datasource.url=jdbc:postgresql://localhost:5432/todo\_db

spring.datasource.username=postgres

spring.datasource.password=root

spring.jpa.hibernate.ddl-auto=update



2\.  to Run Backend

1\. Open terminal inside the backend folder.

2\. Run:

bash

mvn spring-boot:run

3\. Backend will start at:

http://localhost:8081



3\.  to Run Frontend

1\. Open terminal inside the frontend folder.

2\. Install dependencies:

bash

npm install

3\. Start React app:

bash

npm start

4\. Frontend runs at: `http://localhost:3000`

> Make sure the backend is running before starting the frontend.



4\. List of API Endpoints

Base URL: http://localhost:8081/todos

Method    Endpoint        Description

POST      /todos          Create new todo

GET       /todos          Get all todos

GET       /todos/{id}     Get todo by id

PUT       /todos/{id}     Update todo

DELETE    /todos/{id}     Delete todo



