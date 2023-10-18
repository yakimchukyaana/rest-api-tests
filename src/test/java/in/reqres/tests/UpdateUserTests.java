package in.reqres.tests;

import in.reqres.models.update.UpdateUserModel;
import in.reqres.models.update.UpdateUserResponseModel;
import org.junit.jupiter.api.Test;

import static in.reqres.specs.RequestSpec.*;
import static io.restassured.RestAssured.given;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

public class UpdateUserTests extends TestBase {

    @Test
    void updateUserInfoTest() {
        UpdateUserModel updateData = new UpdateUserModel();
        updateData.setName("morpheus");
        updateData.setJob("zion resident");

        UpdateUserResponseModel response = step("Update user info", () ->
                given(requestSpec)
                        .body(updateData)
                        .when()
                        .patch("/users/2")
                        .then()
                        .spec(userResponse200Spec)
                        .extract().as(UpdateUserResponseModel.class));
        step("Verify response", () -> {
            assertThat(response.getName()).isEqualTo("morpheus");
            assertThat(response.getJob()).isEqualTo("zion resident");
        });
    }
}