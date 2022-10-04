package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.notNullValue;

public class ReqresGetSpecs {
    public static RequestSpecification singleUserSpecs = with()
            .basePath("/users?page=2")
            .log().uri()
            .log().body();




    public static ResponseSpecification singleUserResponseSpecs = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .build();

    public static RequestSpecification singleUserNotFoundSpecs = with()
            .basePath("/users/23")
            .log().uri()
            .log().body();

    public static ResponseSpecification singleUserNotFoundResponseSpecs = new ResponseSpecBuilder()
            .expectStatusCode(404)
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .build();
}
