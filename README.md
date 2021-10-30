# tcbp-microservices-homework


## application.properties suggestion:

The database will be shared by all microservices, each one will be responsible for one table. The name of the database is `CRMDatabase`.
Username is `sa` and no password. This I think is the standard for H2 databases.


    spring.application.name=<name of the service here>
    server.port=<port number>
    spring.jpa.defer-datasource-initialization=true
    spring.datasource.url=jdbc:h2:mem:CRMDatabase
    spring.datasource.driver-class-name=org.h2.Driver
    spring.h2.console.enabled=true
    spring.datasource.username=sa
    spring.datasource.password=
    spring.jpa.show-sql=true


## salesrep-service
port: 8100
