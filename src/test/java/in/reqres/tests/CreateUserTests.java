package in.reqres.tests;

import in.reqres.models.create.CreateUserModel;
import in.reqres.models.create.CreateUserResponseModel;
import org.junit.jupiter.api.Test;

import static in.reqres.specs.RequestSpec.createUserResponse201Spec;
import static in.reqres.specs.RequestSpec.requestSpec;
import static io.restassured.RestAssured.given;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

public class CreateUserTests extends TestBase {

    @Test
    void successfullyCreateUserTest() {
        CreateUserModel createData = new CreateUserModel();
        createData.setName("morpheus");
        createData.setJob("leader");

        CreateUserResponseModel response = step("Create user", () ->
                given(requestSpec)
                        .body(createData)
                        .when()
                        .post("/users")
                        .then()
                        .spec(createUserResponse201Spec)
                        .extract().as(CreateUserResponseModel.class));
        step("Verify response", () -> {
            assertThat(response.getName()).isEqualTo("morpheus");
            assertThat(response.getJob()).isEqualTo("leader");
        });
    }
}