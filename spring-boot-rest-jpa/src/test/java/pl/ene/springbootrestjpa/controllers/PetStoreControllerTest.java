package pl.ene.springbootrestjpa.controllers;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.openapitools.client.api.PetsApi;
import org.openapitools.client.model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import pl.ene.springbootrestjpa.domain.Customer;
import pl.ene.springbootrestjpa.repository.CustomerRepository;

//@RunWith(SpringRunner.class)
//@RunWith(SpringRunner.class)
@SpringBootTest
public class PetStoreControllerTest {

    @MockBean
    private PetsApi petStoreApi;

    @InjectMocks
    @Autowired
    private PetStoreController controller;

    @Test
    public void getPets_shouldReturn_empty_list() throws Exception {

        List<Pet> list = Lists.emptyList();
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        when(petStoreApi.getPetsPetsGet()).thenReturn(list);
        mockMvc.perform(get("/pets")).andExpect(status().isOk());
    }

}
