# Microservices Architecture

This project is developed using the microservices architecture, which is a software design pattern that structures an application as a collection of small, independent, and loosely coupled services. In this project, there are four microservices that are integrated together using Spring Cloud Gateway and Eureka Server.

## Services
### 1. User Microservice
    This microservice is responsible for handling all the user-related operations. Users can perform various tasks, such as creating a new account, updating their profile, and getting their ratings for particular hotels.

### 2. Rating Microservice
    This microservice manages all the operations related to hotel ratings. Users can view the hotels they have rated, and hotels can access their ratings and reviews.

### 3. Hotel Microservice
    This microservice handles all the operations related to hotels, such as creating a new hotel, updating the details of an existing hotel, and deleting a hotel.

### 4. Identity Microservice
    This microservice is responsible for user authentication using JWT tokens. New users must register here first, and then they can generate a token using their credentials to access other microservices.

## Gateway and Service Registry
###    Spring Cloud Gateway
Spring Cloud Gateway is used as the gateway for this project, which is responsible for routing requests to different microservices. It also handles load balancing and other cross-cutting concerns.

### Eureka Server
Eureka Server is used as the service registry in this project, which is responsible for maintaining a list of available microservices and their instances. The gateway uses this registry to route requests to the appropriate microservice.

### Conclusion
Using microservices architecture allows for greater scalability, flexibility, and resilience in application development. It also enables easier testing, deployment, and maintenance of the application. By leveraging the different microservices and integrating them using Spring Cloud Gateway and Eureka Server, this project demonstrates the power and benefits of this architecture.











