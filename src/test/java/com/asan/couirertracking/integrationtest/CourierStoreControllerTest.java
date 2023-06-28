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
public class CourierStoreControllerTest {

    @Autowired
    public WebTestClient webTestClient;

    @Test
    public void courierTotalDistanceReactive_Test() {
        this.webTestClient
                .post()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/courierStore/saveReport")
                                .queryParam("courierId", "111")
                                .build())
                .bodyValue("{" +
                        "    \"courierId\":111," +
                        "    \"locationDto\":" +
                        "    {" +
                        "    \"lat\":44.055783," +
                        "    \"lng\":36.21523" +
                        "}" +
                        "    }")
                .exchange()
                .expectStatus().isOk();

    }

}