package guru.qa;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.is;

public class ApiTests {

    @Test
    void getSimpleTest() {
        get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("total", is(12));
    }
}
