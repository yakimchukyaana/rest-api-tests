package in.reqres.tests;

import org.junit.jupiter.api.Test;

import static in.reqres.specs.RequestSpec.requestSpec;
import static in.reqres.specs.RequestSpec.userNotFoundResponse404Spec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

public class GetUserTests extends TestBase {

    @Test
    void singleUserNotFoundTest() {
        step("Verify User not found request", () ->
                given(requestSpec)
                        .when()
                        .get("/users/42")
                        .then()
                        .spec(userNotFoundResponse404Spec)
        );
    }
}