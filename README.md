# Contact Book

A typical contact book which allows functionalities mentioned in BRD :-

    -  Allows User to register using username and password
    -  Allows User to login (authenticate) using his credentials
    -  Allows User to create contact having first-name and last-name
    -  Allows User to update contact
    -  Allows User to associate 0 or more phone numbers to a contact
    -  Allows User to delete a contact
    -  Allows User to list all his contacts
    -  Contacts are mutually exclusive and two users do share same contacts
    -  More than one contacts can have same phone numbers
 
 ##### Add-ons
    - Phone Number Validation and formatting before inserting into database
    - JWT Token for authentication
    - API to GET contact by Id
    - No Duplicacy of phone numbers for same contact

### Tools and Technologies
    Java 8
    Spring-Boot
    Spring JPA
    Spring Security
    JWT
### How to run it?
    mvn spring-boot:run &
    
### Docs Please!

A complete list of API docs are available [here](https://documenter.getpostman.com/view/31726/central-way-apis/6fWy4UV).

![Just A View](/src/main/resources/APIs.PNG)