# creditcardprovider

**Project**
This app exposes two REST end points :

POST /credit/addCard - creates a new credit card with given card no against the given customer name

GET /credit/cards/ - get  all credit cards

**Tech / Framework used**

Spring Boot
Spring REST
JDK 8
Lombok Processors
Springfox Swagger2 / Swagger UI
Junit-4 / Mockito

**How to run this application**
1. Clone this repo into your local system and import in an IDE (IntelliJ/Eclipse)

2. Run the CreditcardproviderApplication.java class which should start the embedded tomcat server in localhost:8080

3. Open the Swagger UI - http://localhost:8080/swagger-ui.html#/

4. Create credit cards using the POST end point and get all credit card information using GET end point
