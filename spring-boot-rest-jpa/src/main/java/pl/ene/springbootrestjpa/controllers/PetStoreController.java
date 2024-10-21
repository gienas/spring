package pl.ene.springbootrestjpa.controllers;

import org.openapitools.client.api.PetsApi;
import org.openapitools.client.model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.ene.springbootrestjpa.domain.Greeting;
import pl.ene.springbootrestjpa.domain.Person;

import java.util.List;

@RestController
public class PetStoreController {

	private final PetsApi petStoreApi;

	@Autowired
	public PetStoreController(PetsApi petStoreApi) {
		this.petStoreApi = petStoreApi;
	}

	@RequestMapping("/pets")
	public List<Pet> pets() {
		return petStoreApi.getPetsPetsGet();
	}


}


