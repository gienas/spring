// src/test/java/pl/ene/simpleproducer/BaseContractTest.java
package pl.ene.simpleproducer;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.ene.simpleproducer.controllers.EvenOddController;

@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class BaseContractTest {

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(new EvenOddController());
    }
}