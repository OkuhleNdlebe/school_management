package za.ac.cput.group6.controller.lookup;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.group6.domain.lookup.Address;
import za.ac.cput.group6.domain.lookup.City;
import za.ac.cput.group6.domain.lookup.Country;
import za.ac.cput.group6.factory.lookup.AddressFactory;
import za.ac.cput.group6.factory.lookup.CityFactory;
import za.ac.cput.group6.factory.lookup.CountryFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CountryControllerTest {

    @Autowired
    private CountryController controller;
    @Autowired private TestRestTemplate restTemplate;
    private String baseUrl = "http://localhost:8080/country";
    private static Country country;

    @Order(0)
    @Test
    void init() {
        country = CountryFactory.createCountry("909", "Dubai");
        assertNotNull(controller);
        assertNotNull(country);
        System.out.println(country);
    }

    @Order(1)
    @Test
    void save(){
        System.out.println(baseUrl);
        String url = baseUrl + "/create";
        System.out.println(url);
        ResponseEntity<Country> response = this.restTemplate
                .postForEntity(url, this.country, Country.class);
        System.out.println(response);
        assertAll(
                () -> assertEquals(HttpStatus.OK,response.getStatusCode()),
                () -> assertNotNull(response.getBody())
        );
    }

    @Order(2)
    @Test
    void read(){
        String url = baseUrl + "/read/" + this.country.getName();
        System.out.println(url);
        ResponseEntity<Country> response =
                this.restTemplate.getForEntity(url, Country.class);
        System.out.println(response);
        assertAll(
                ()-> assertEquals(HttpStatus.OK, response.getStatusCode()),
                ()-> assertNotNull(response.getBody())
        );
    }

    @Order(4)
    @Test
    void delete(){
        String url = baseUrl + "/delete";
        System.out.println(url);
        ResponseEntity<Country> response = this.restTemplate
                .postForEntity(url, this.country, Country.class);
        this.restTemplate.delete(url);
    }
    @Order(3)
    @Test
    void getAll(){
        String url = baseUrl + "/getall";
        System.out.println(url);
        ResponseEntity<Country[]> response =
                this.restTemplate.getForEntity(url, Country[].class);
        System.out.println(response.getBody());
        assertAll(
                () -> assertEquals(1, response.getBody().length)
        );
    }
}