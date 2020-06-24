# Matterwiki for Java



According to the react.js project [Matterwiki](https://github.com/Matterwiki/Matterwiki), 
I rewrite it in Java  based on Springboot. 


## Get Started

Just run this command  to start the jar,and the server's port is 8080


```
java -jar  matterwiki-4j-boot-1.0-SNAPSHOT.jar
```

When the program finish starting ,visit this url 

```http://localhost:8080/```


## Run the project

Maven Command|Description
:---|:---:
mvn clean install |	performs a build
mvn clean package |	performs a build
mvn spring-boot:run|	(from matterwiki-4j-boot module) starts Matterwiki4j on a Tomcat instance at http://localhost:8080/


You can change the server's port with editing the property file  named `appliaction.properties` in the jar.





## Required environment  
✨Java: at least version 1.8


This program will auto create a h2 database, 
 you don't need to configure a database url.
 
##  API
API doc is showed on the page swagger-ui.html.   
When the program finish starting,visit this url to read the api doc.
```http://localhost:8080/swagger-ui.html``` 

<img src="https://github.com/volunL/Matterwiki4j/blob/master/screenshot_swagger.png?raw=true" />



## Under the hood
Matterwiki4j uses React.js for the front-end and Springboot for the back-end.  
Its default database is h2.


<img width="30%"   src="http://www.h2database.com/html/images/h2-logo-2.png" />  

<img width="20%" src="https://res.cloudinary.com/practicaldev/image/fetch/s--Hx4s8tX---/c_limit,f_auto,fl_progressive,q_80,w_180/https://dev-to-uploads.s3.amazonaws.com/uploads/badge/badge_image/26/react-sticker.png" />

<img width="30%"  src="https://spring.io/images/spring-logo-9146a4d3298760c2e7e49595184e1975.svg"/>

  


## Screen Shot

<img src="https://github.com/volunL/Matterwiki4j/blob/master/screenshot.png?raw=true" />

<img src="https://github.com/volunL/Matterwiki4j/blob/master/screenshot_content.png?raw=true" />

