# eCasofond backend

## Project setup

1. clone the repository
2. ensure you have [jdk 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) installed &
   added to your **PATH** as **JAVA_HOME**
3. create your own database based on the [database dump](db.sql)
4. connect the database by changing the [application.properties](src/main/resources/application.yml) file
5. generate **RSA key pair** and put it into [resources/certs](src/main/resources/certs) directory (private.pem &
   public.pem)
6. build & run
7. available endpoints can be found at http://localhost:8080/swagger-ui/index.html

## Authors
- [Lukáš Bien](https://github.com/4iwen)
