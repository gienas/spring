package pl.ene.springbootrestjpa.config;

import org.openapitools.client.ApiClient;
import org.openapitools.client.api.PetsApi;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestClientsConfig {
//   Manual way of creating the client
//    @Bean
//    PetsApi petsApiClient() {
//        PetsApi api = new PetsApi();
//        //This value should be read from a config component
//        api.getApiClient().setBasePath("http://localhost:8080/v2");
//        return api;
//    }

    @Bean
    PetsApi petsApiClient(RestTemplateBuilder restTemplateBuilder) {
        RestTemplate restTemplate =  restTemplateBuilder.build();
        ApiClient api = new ApiClient(restTemplate);
        //This value should be read from a config component
        api.setBasePath("http://localhost:8080/v1");
        return new PetsApi(api);
    }

}
