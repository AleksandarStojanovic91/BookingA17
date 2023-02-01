package api;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class APITest {
    @Test
    public void getPet(){
        RestAssured
                .given()
                .when()
                    .get("https://petstore.swagger.io/v2/pet/1")
                .then()
                    .assertThat()
                    .statusCode(200);
    }
}
