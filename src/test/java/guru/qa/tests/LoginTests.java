package guru.qa.tests;

import guru.qa.models.pojo.LoginBodyPOJOModel;
import guru.qa.models.pojo.LoginResponsePOJOModel;
import guru.qa.models.lombok.LoginBodyModel;
import guru.qa.models.lombok.LoginResponseModel;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class LoginTests extends BaseTest {

    @Test
    void successfulLoginTest() {

        LoginBodyPOJOModel authData = new LoginBodyPOJOModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");

        LoginResponsePOJOModel response = given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(authData)
                .when()
                .post("/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponsePOJOModel.class);

        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    void successfulLoginTestWithLombok() {

        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");

        LoginResponseModel response = given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(authData)
                .when()
                .post("/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseModel.class);

        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }


    @Test
    void unsuccessfulLoginTest() {
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("{ \"email\": \"eve.holt@reqres.in\"}")
                .when()
                .post("/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }
}
