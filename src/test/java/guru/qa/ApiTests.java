package guru.qa;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.is;

public class ApiTests {

    @Test
    void getSimpleTest() {
        given()
                .log().method()
                .log().uri()
            .when()
                .get("https://reqres.in/api/users?page=2")
            .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("total", is(12));
    }
}
