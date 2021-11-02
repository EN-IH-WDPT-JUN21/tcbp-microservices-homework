# tcbp-microservices-homework


## application.properties suggestion:

<strong> ** IMPORTANT - H2 Database Application needs to be run to Allow Databases to work accross microservices ** </strong>

The database will be shared by all microservices, each one will be responsible for one table. The name of the database is `CRMDatabase`.
Username is `sa` and no password. This I think is the standard for H2 databases.


    spring.application.name=<name of the service here>
    server.port=<port number>
    spring.datasource.url=jdbc:h2:tcp://localhost:9090/mem:CRMDatabase
    spring.datasource.driver-class-name=org.h2.Driver
    spring.h2.console.enabled=true
    spring.datasource.username=sa
    spring.datasource.password=
    spring.jpa.show-sql=true


## salesrep-service
port: 8100

GET: /salesrep

POST: /salesrep

- "name": String

## contact-service
port: 8003

GET:
- /api/contact  
- /api/contact/{id}

POST: /api/contact

- "contactName": String
- "phoneNumber": *Phone number that must be 6-15 digits and that can include the country code*
- "email": String *(must have the correct format of an e-mail address)*
- "companyName": String

## opportunity-service
port: 8004

GET:
- /api/opportunity
- /api/opportunity/{id}

POST: /api/opportunity

- "product": String *(can only be "box", "hybrid" or "flatbed" - case-insensitive)*
- "quantity": int
- "decisionMaker": *id of an existing contact*
  
  
