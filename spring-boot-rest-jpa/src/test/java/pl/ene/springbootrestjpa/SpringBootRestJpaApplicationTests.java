package pl.ene.springbootrestjpa;

import static com.toomuchcoding.jsonassert.JsonAssertion.assertThatJson;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.assertj.core.api.Assertions.assertThat;


import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.response.ResponseOptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pl.ene.springbootrestjpa.controllers.GreetingsController;

@SpringBootTest
public class SpringBootRestJpaApplicationTests {

	@BeforeAll
	public static void setup() {
		RestAssuredMockMvc.standaloneSetup(new GreetingsController());
	}

	@Test
	public void greetingTest_shouldReturnHello() {
		//when
		ResponseOptions<?> response = given().get("/test");

		// then:
		assertThat(response.statusCode()).isEqualTo(200);
		//and
		// and:
		DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
		assertThatJson(parsedJson).field("['id']").isEqualTo("1");
		//assertThatRejectionReasonIsNull(parsedJson.read("$.['rejection.reason']"));
	}

	@Test
	public void contextLoads() {

	}

}
