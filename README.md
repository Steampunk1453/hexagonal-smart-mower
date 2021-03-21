### About

Application to remotely control mowers in a plateau

### Testing project in local environment

- How to run project:
    * You need to install Gradle
    * From the root of project
    * Execute `gradle bootRun`
    * Project uses Swagger for API documentation you can see endpoints info in http://localhost:8080/swagger-ui.html 

- How to test project endpoints:
  * Go to: c
  * Also, you can use cURL client or Postman
  * Examples  
      * First endpoint: /mowers/send {POST} 
        
        Body:
        {
            "instructions": "5 5,1 2 N,LMLMLMLMM,3 3 E,MMRMMRMRRM"
        }
        
      * Second endpoint: /mowers/execute/{plateauId} {POST}
        
        Use the UUID from the previous endpoint response

        plateauId format example: 4d03f6f0-68e2-4ef2-996e-4e193cfeb1d1
        
- How to run project tests:
    * From the root of project
    * Execute `gradle test` 

- How to build project:
    * From the root of project
    * Execute `gradle build` 
    * After, you can go to /build/libs
    * Execute this command: `java -jar smart-mower-0.0.1-SNAPSHOT.jar` to launch project

### Technical details
It implements a Domain / Hexagonal (Ports and Adapters) approach with Kotlin and MockK for unit tests

### Tech stack
- Kotlin
- Spring Boot
- Gradle
- MockK
- Swagger

### TODO
* Add logs
* Add ControllerAdvice to handle exceptions in one point
* Add more integration tests
* Allow to send files in CSV format

### More info
You can see my personal code projects in Github:

* [https://github.com/Steampunk1453](https://github.com/Steampunk1453)

Thank you for reading!
