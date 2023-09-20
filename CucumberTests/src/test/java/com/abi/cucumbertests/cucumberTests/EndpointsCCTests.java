package com.abi.cucumbertests.cucumberTests;





import com.abi.cucumbertests.petRepo.PetRepo;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
import wiremock.org.apache.hc.client5.http.classic.methods.HttpPost;
import wiremock.org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import wiremock.org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import wiremock.org.apache.hc.core5.http.io.entity.StringEntity;

import static org.junit.jupiter.api.Assertions.*;


@CucumberContextConfiguration
@CucumberOptions(features = "src/test/resources")
@SpringBootTest
public class EndpointsCCTests {
    String urlgetAll = "http://localhost:8080/pet/getAll";
    String urlgetStatus = "http://localhost:8080/pet/getPetStatus";
    String responseGetAll = "";
    String responseGetStatus = "";


    @Autowired
    private PetRepo petRepo;


    @When("the client calls \\/pet\\/getAll")
    public void the_client_calls_pet_get_all() {
        RestTemplate restTemplate = new RestTemplate();
        responseGetAll = restTemplate.getForObject(urlgetAll, String.class);
    }

    @Then("the client get all pets")
    public void the_client_get_all_pets() {
        assertNotNull(responseGetAll);
    }


    @When("the client calls \\/pet\\/getPetStatus")
    public void the_client_calls_pet_get_pet_status() {
        RestTemplate restTemplate = new RestTemplate();
        responseGetStatus = restTemplate.getForObject(urlgetStatus, String.class);
    }


    @Then("the client get the status")
    public void the_client_get_the_status() {
        assertTrue(
                responseGetStatus.equals("available") ||
                        responseGetStatus.equals("sold") ||
                        responseGetStatus.equals("pending")

        );
    }

    @When("the client calls addPet")
    public void the_client_calls_add_pet() {


        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            String jsonData = "{ \"id\": 7, \"category\": { \"id\": 9, \"name\": \"string\" }, \"name\": \"Marc\", \"photoUrls\": [ \"string\" ], \"tags\": [ { \"id\": 7, \"name\": \"string\" } ] }";
            HttpPost request = new HttpPost("http://localhost:8080/pet");
            StringEntity params = new StringEntity(jsonData);
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            httpClient.execute(request);

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Then("the client give me a pet")
    public void the_client_give_me_a_pet() {
        String petStatus = petRepo.findById(7).get().getName();
        assertEquals("Marc", petStatus);
    }

}
