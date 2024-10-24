package pl.ene.springbootrestjpa.controllers;

import org.openapitools.client.api.EvenOddControllerApi;
import org.openapitools.client.api.PetsApi;
import org.openapitools.client.model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@RestController
public class SimpleProducerController {

	private final EvenOddControllerApi oddControllerApi;

	@Autowired
	public SimpleProducerController( EvenOddControllerApi oddControllerApi ) {
		this.oddControllerApi = oddControllerApi;
	}

	@GetMapping("/calculate")
	public ResponseEntity<String> isNumberPrime(@RequestParam("number") Integer number) {
		try {
			String result = oddControllerApi.isNumberPrime(number);
			return ResponseEntity.ok(result);
		} catch (HttpClientErrorException e) {
			return ResponseEntity.status(e.getStatusCode()).body("Client error: " + e.getMessage());
		} catch (HttpServerErrorException e) {
			return ResponseEntity.status(e.getStatusCode()).body("Server error: " + e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + e.getMessage());
		}
	}


}
