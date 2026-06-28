This is a basic ecommerce backend project for practicing SpringBoot, REST API, Spring security, etc.

To run it create "application.properties" file in src/main/resources" folder.
#
In application.properties add:
spring.application.name=ecommerce-backend

spring.datasource.url = #YOUR DATABASE URL
spring.datasource.username= #YOU DATABASE USERNAME
spring.datasource.password= #YOURDATABASEPASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

jwt.secret=YOUR SECRETKEY
#
After this run the file "main/java/EcommerceBackendApplication"
