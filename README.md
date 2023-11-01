# Product Price services

The aim of this application is to retrieve, for a Product of a given Brand, the Price data that applies for a specific date.

## Technologies and frameworks

For the development and testing of this application, the next technologies and frameworks have been used:

* Java 11
* Spring Boot 2.7.17
* Spring Data JPA
* Lombok
* MapStruct
* JUnit 5
* MockMvc

## Running the application

Once the application has been cloned or downloaded from Git repository, next steps must be done to run the application:

* Locate in the project root folder and execute the next command:

```sh
mvn install
```

* Once the build is completed, execute next command to run the application:

```sh
java -jar target/prices-1.0.0-SNAPSHOT.jar
```

## Service request

To execute the service, a GET request must be made to the following endpoint:

* /brands/{brandId}/products/{productId}/price?applicationDate={applicationDate}

  * {brandId}: Product Brand id.
  * {productId}: Price id.
  * {applicationDate}: date for which Price is applied. This date must have the next format: yyyy-MM-dd-HH.mm.ss

### Request example

* Request
 
GET http://localhost:8080/brands/1/products/35455/price?applicationDate=2020-06-14-10.00.00

* Response
  * Status: 200
  * Body:

```json
{
    "productId": 35455,
    "brandId": 1,
    "priceList": 1,
    "startDate": "2020-06-14-00.00.00",
    "endDate": "2020-12-31-23.59.59",
    "price": 35.50,
    "currency": "EUR"
}
```
