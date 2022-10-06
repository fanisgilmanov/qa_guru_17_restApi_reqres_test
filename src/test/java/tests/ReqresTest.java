package tests;

import io.restassured.RestAssured;
import models.lombok.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static specs.ReqresCreateUpdateSpecs.*;
import static specs.ReqresGetSpecs.*;
import static specs.ReqresLoginSpecs.loginResponseSpec;
import static specs.ReqresLoginSpecs.loginSpec;

public class ReqresTest {
    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "https://reqres.in/api";
    }



    @Test
    @DisplayName("Проверка GET запроса SINGLE USER (GROOVY)")
    void checkUsersEmail(){
        given()
                .spec(singleUserSpecs)
                .when()
                .get("")
                .then()
                .spec(singleUserResponseSpecs)
                .body("data.findAll{it.email =~/.*?@reqres.in/}.email.flatten()",
                        hasItem("janet.weaver@reqres.in"));
    }

    @Test
    @DisplayName("Проверка GET запроса SINGLE USER")
    void singleUserTest() {

        ReqresResponseGetModels response = given()
                .spec(singleUserSpecs)
                .when()
                .get()
                .then()
                .spec(singleUserResponseSpecs)
                .extract().as(ReqresResponseGetModels.class);


        assertThat(response.getPage()).isEqualTo("1");
        assertThat(response.getPer_page()).isEqualTo("6");
        assertThat(response.getTotal()).isEqualTo("12");
        assertThat(response.getTotal_pages()).isEqualTo("2");

    }


    @Test
    @DisplayName("Проверка GET запроса SINGLE USER NOT FOUND")
    void singleUserNotFoundTest() {
        given()
                .spec(singleUserNotFoundSpecs)
                .when()
                .get()
                .then()
                .spec(singleUserNotFoundResponseSpecs);
    }

    @Test
    @DisplayName("Проверка POST запроса CREATE")
    void createUserTest() {
        ReqresRequestCreateUpdateModels body = new ReqresRequestCreateUpdateModels();
        body.setName("morpheus23");
        body.setJob("leader12");

        ReqresResponseCreateUpdateModels responsePost = given()
                .spec(createUpdateSpec)
                .body(body)
                .when()
                .post()
                .then()
                .spec(createResponseSpec)
                .extract()
                .as(ReqresResponseCreateUpdateModels.class);

        assertThat(responsePost.getName()).isEqualTo("morpheus23");
        assertThat(responsePost.getJob()).isEqualTo("leader12");
        ;
    }

    @Test
    @DisplayName("Проверка PUT запроса UPDATE")
    void updateUserTest() {
        ReqresRequestCreateUpdateModels body = new ReqresRequestCreateUpdateModels();
        body.setName("morpheus-323");
        body.setJob("team-leader12");

        ReqresResponseCreateUpdateModels responsePut = given()
                .spec(createUpdateSpec)
                .body(body)
                .when()
                .put()
                .then()
                //.spec(updateResponseSpec)
                .extract()
                .as(ReqresResponseCreateUpdateModels.class);

        assertThat(responsePut.getName()).isEqualTo("morpheus-323");
        assertThat(responsePut.getJob()).isEqualTo("team-leader12");
    }

    @Test
    @DisplayName("Проверка POST запроса  LOGIN - SUCCESSFUL ")
    void loginSuccessfulTest() {
        ReqresRequestModels body = new ReqresRequestModels();
        body.setEmail("eve.holt@reqres.in");
        body.setPassword("cityslicka");

        ReqresResponseModels response = given()
                .spec(loginSpec)
                .body(body)
                .when()
                .post()
                .then()
                .spec(loginResponseSpec)
                .extract()
                .as(ReqresResponseModels.class);

        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");


    }

}
