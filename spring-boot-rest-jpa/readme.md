# How to start ?

    $ mvn clean install spring-boot:run

Build sping0boot-simple-producer might be needed before

    $ mvn clean install spring-boot:run
Go to http://localhost:8888/swagger-ui/index.html 

# Contract tests

- Producer defines contract in DSL see spring-boot-simple-producer
- The contract is being provided as artifact during build 
  - plugin spring-cloud-contract-maven-plugin
    - Generates producer tests
    - Generates stub e.g .m2/repository/pl/ene/spring-boot-simple-producer/0.0.1-SNAPSHOT/spring-boot-simple-producer-0.0.1-SNAPSHOT-stubs.jar
  - Consumer uses the stub to prepare config for Wiremock
      - stub is being used by annotation:
        ```java
        @AutoConfigureStubRunner(stubsMode = StubRunnerProperties.StubsMode.LOCAL, ids = "pl.ene:spring-boot-simple-producer:0.0.1-SNAPSHOT:stubs:8080")
        //
            @Autowired
            MockMvc mockMvc;  
        ```
        - This is mocking the producer according DSL definition
        - We tests always our own controllers !!!
          - The controller call another REST -> the request ends up in server started during tests based on DSL