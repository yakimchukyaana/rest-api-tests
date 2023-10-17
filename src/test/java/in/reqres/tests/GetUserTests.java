package in.reqres.tests;

import org.junit.jupiter.api.Test;

import static in.reqres.specs.UserSpec.userNotFoundResponseSpec;
import static in.reqres.specs.UserSpec.userRequestSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

public class GetUserTests extends TestBase {

    @Test
    void singleUserNotFoundTest() {
        step("Verify User not found request", () ->
                given(userRequestSpec)
                        .when()
                        .get("/users/42")
                        .then()
                        .spec(userNotFoundResponseSpec)
        );
    }
}