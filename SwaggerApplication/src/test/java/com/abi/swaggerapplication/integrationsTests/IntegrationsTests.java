//package com.abi.swaggerapplication.integrationsTests;
//
//
//import com.abi.swaggerapplication.SwaggerApplication;
//import com.abi.swaggerapplication.config.ContainersEnvironment;
//import com.abi.swaggerapplication.model.Category;
//import com.abi.swaggerapplication.model.Pet;
//import com.abi.swaggerapplication.model.Tag;
//import com.abi.swaggerapplication.repository.CategoryRepo;
//import com.abi.swaggerapplication.repository.PetRepo;
//import com.abi.swaggerapplication.repository.TagRepo;
//import com.abi.swaggerapplication.service.PetService;
//import com.github.springtestdbunit.DbUnitTestExecutionListener;
//import com.github.springtestdbunit.annotation.DbUnitConfiguration;
//import com.github.tomakehurst.wiremock.WireMockServer;
//import com.github.tomakehurst.wiremock.client.WireMock;
//import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ValueSource;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.TestExecutionListeners;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
//import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
//import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//
//@ActiveProfiles("test")
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(classes = SwaggerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
//@ContextConfiguration
//@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
//@DbUnitConfiguration(databaseConnection = {"dbUnitDataSource"})
//public class IntegrationsTests extends ContainersEnvironment {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private PetRepo petRepo;
//
//    @Autowired
//    private TagRepo tagRepo;
//
//    @Autowired
//    private CategoryRepo categoryRepo;
//
//    @Autowired
//    private PetService petService;
//
//    private WireMockServer wireMockServer;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(8082));
//
//        wireMockServer.start();
//
//
//    }
//
//
//    @AfterEach
//    public void tearDown() {
//        wireMockServer.stop();
//    }
//
//
//    @Test
//    public void testWiremock() {
//        System.out.println(wireMockServer.baseUrl());
//        assertTrue(wireMockServer.isRunning());
//    }
//
//
//    @ParameterizedTest
//    @ValueSource(strings = {"Sold", "Available", "Pending"})
//    public void testAddNewPetWithStatus(String status) throws Exception {
//
//        wireMockServer.stubFor(WireMock.get("/api/getPetStatus").willReturn(aResponse().withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE).withBody(status)));
//
//        Pet pet = new Pet();
//        pet.setId(7);
//        pet.setCategory(new Category(9, "string"));
//        pet.setName("Marc");
//        pet.setPhotoUrls(Collections.singletonList("string"));
//        pet.setTags(Collections.singletonList(new Tag(7, "string")));
//
//        petService.addPet(pet);
//        String petStatus = petRepo.findById(7).get().getStatus();
//
//        assertEquals(status, petStatus);
//    }
//
//    @Test
//    public void testGetPets() throws Exception {
//
//        Pet pet = new Pet();
//        pet.setId(7);
//
//        Category category = new Category();
//        category.setId(9);
//        category.setName("string");
//        pet.setCategory(category);
//
//        pet.setName("Marc");
//
//        List<String> photoUrls = new ArrayList<>();
//        photoUrls.add("string");
//        pet.setPhotoUrls(photoUrls);
//
//        List<Tag> tags = new ArrayList<>();
//        Tag tag = new Tag();
//        tag.setId(7);
//        tag.setName("string");
//        tags.add(tag);
//        pet.setTags(tags);
//
//        categoryRepo.save(category);
//        tagRepo.saveAll(tags);
//        petRepo.save(pet);
//        List<Pet> list = petRepo.findAll();
//
//
//        assertEquals(2, list.size());
//
//    }
//
//
//    @Test
//    public void getStatusFromPet() throws Exception {
//
//        wireMockServer.stubFor(WireMock.get("/api/getPetStatus").willReturn(aResponse().withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE).withBody("Pending")));
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/pet/getPetStatus")).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
//
//    }
//
//
//}
//
