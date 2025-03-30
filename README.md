Spring Boot API Clients Comparison 🚀
A hands-on project demonstrating API communication in microservices using RestTemplate, WebClient, and Feign Client.

📌 Project Overview
This project showcases three different ways to call REST APIs in a microservices architecture using Spring Boot. The goal is to compare RestTemplate, WebClient, and Feign Client while making the same API calls to a User Service that provides CRUD operations.

🔹 Microservices Included:
1️⃣ User-Service: A core service that provides CRUD operations for users.
2️⃣ RestTemplate-Service: Calls User-Service using RestTemplate (synchronous/blocking).
3️⃣ WebClient-Service: Calls User-Service using WebClient (reactive/non-blocking).
4️⃣ FeignClient-Service: Calls User-Service using Feign Client (declarative and easier approach).

🚀 Features
✔️ Compare different API clients – RestTemplate, WebClient, and Feign Client.
✔️ CRUD operations – Create, Read, Update, and Delete users.
✔️ Microservices architecture – Separate services interacting via REST APIs.
✔️ PostgreSQL integration – User data is stored in PostgreSQL.
✔️ Swagger Documentation – Easily test APIs using Swagger UI.

## 🔗 Swagger UI Links  
Each microservice provides a Swagger UI for easy API testing.  

- **Feign Client Service**  
  [http://localhost:8200/swagger-ui/index.html](http://localhost:8200/swagger-ui/index.html)  

- **UseRestTemplate Service**  
  [http://localhost:8087/swagger-ui/index.html](http://localhost:8087/swagger-ui/index.html)  

- **RestTemplate Service**  
  [http://localhost:8086/swagger-ui/index.html](http://localhost:8086/swagger-ui/index.html)  

- **WebClient Service**  
  [http://localhost:8088/swagger-ui/index.html](http://localhost:8088/swagger-ui/index.html)  
