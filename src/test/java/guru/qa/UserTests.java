package guru.qa;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class UserTests extends BaseTest {

    @Test
    void getUserListTest() {
        given()
                .log().method()
                .log().uri()
                .baseUri(baseURI)
                .when()
                .get("users?page=2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("total", is(12));
    }

    @Test
    void getSingleUserTest() {
        given()
                .log().method()
                .log().uri()
                .baseUri(baseURI)
                .when()
                .get("users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.first_name", is("Janet"))
                .body("data.last_name", is("Weaver"));
    }

    @Test
    void userNotFoundTest() {
        given()
                .log().method()
                .log().uri()
                .baseUri(baseURI)
                .when()
                .get("users/42")
                .then()
                .log().status()
                .log().body()
                .statusCode(404);
    }
}
