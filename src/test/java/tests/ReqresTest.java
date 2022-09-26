package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class ReqresTest {

    @Test
    @DisplayName("Проверка GET запроса SINGLE USER")
    void singleUserTest(){
        given()
                .log().uri()
                .log().body()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("page", is(2))
                .body("per_page", is(6))
                .body("total", is(12))
                .body("total_pages", is(2));
    }


    @Test
    @DisplayName("Проверка GET запроса SINGLE USER NOT FOUND")
    void singleUserNotFoundTest(){
        given()
                .log().uri()
                .log().body()
                .when()
                .get("https://reqres.in/api/users/23")
                .then()
                .log().status()
                .log().body()
                .statusCode(404);
    }

    @Test
    @DisplayName("Проверка POST запроса CREATE")
    void createUserTest(){
        String bodyUser = "{\n" +
                "\"name\": \"morpheus23\",\n" +
                "\"job\": \"leader12\"\n" +
                "}";

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(bodyUser)
                .when()
                .post("https://reqres.in/api/users/")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus23"))
                .body("job", is("leader12"));
    }

    @Test
    @DisplayName("Проверка PUT запроса UPDATE")
    void updateUserTest(){
        String bodyUser = "{\n" +
                "\"name\": \"morphe-12\",\n" +
                "\"job\": \"team-leader12\"\n" +
                "}";

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(bodyUser)
                .when()
                .put("https://reqres.in/api/users/")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("morphe-12"))
                .body("job", is("team-leader12"));
    }
    @Test
    @DisplayName("Проверка POST запроса  LOGIN - SUCCESSFUL ")
    void loginSuccessfulTest(){
        String bodyUser = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"cityslicka\"\n" +
                "}";

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(bodyUser)
                .when()
                .post("https://reqres.in/api/login/")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));

    }

}
