package com.asan.couirertracking.integrationtest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@DataR2dbcTest
@AutoConfigureWebTestClient
@TestPropertySource(locations = "classpath:application.yaml")
public class CourierControllerTest {

    @Autowired
    public WebTestClient webTestClient;

    @Test
    public void courierTotalDistanceReactive_Test() {
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/courier/totalDistanceReactive")
                                .queryParam("courierId", "111")
                                .build())
                .exchange()
                .expectBody(Double.class)
                .returnResult();

    }

}