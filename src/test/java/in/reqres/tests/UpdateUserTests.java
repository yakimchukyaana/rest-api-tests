package in.reqres.tests;

import in.reqres.models.updateUser.UpdateUserModel;
import in.reqres.models.updateUser.UpdateUserResponseModel;
import org.junit.jupiter.api.Test;

import static in.reqres.specs.UpdateUserSpec.updateUserRequestSpec;
import static in.reqres.specs.UpdateUserSpec.updateUserResponseSpec;
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
                given(updateUserRequestSpec)
                        .body(updateData)
                        .when()
                        .patch("/users/2")
                        .then()
                        .spec(updateUserResponseSpec)
                        .extract().as(UpdateUserResponseModel.class));
        step("Verify response", () -> {
            assertThat(response.getName()).isEqualTo("morpheus");
            assertThat(response.getJob()).isEqualTo("zion resident");
        });
    }
}