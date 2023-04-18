# MicroServices

I learned the concept of microservices through this project . I used 4 microservices in this project . I used spring cloud gateway as gateway in my project .
And Eureka server for service registry .


## 1 . User-MicroService
    This is the main service in which we can able to perform all the user related tasks and in user service we can get ratings of particular user given for particular hotel .

## 2 . Rating MicroService
    This service is used to perform the tasks related to Ratings and in rating service we get the hotels also that which rating is related to which hotel .

## 3 . Hotel Service 
    Service is used to perform all the hotel related tasks like create new hotel , any updation in old records , delete operatins and several others .

## 4 . Identity Service
    This Service is used for Authenticate a user on the basis of JWt token . New user will first register here then after genearting token from their credentials only they will get access of other microservices .
