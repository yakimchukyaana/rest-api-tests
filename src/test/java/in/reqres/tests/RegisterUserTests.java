package in.reqres.tests;

import in.reqres.models.RegisterUser.MissingPasswordResponseModel;
import in.reqres.models.RegisterUser.RegisterUserRequestModel;
import in.reqres.models.RegisterUser.RegisterUserResponseModel;
import org.junit.jupiter.api.Test;

import static in.reqres.specs.RegisterUserSpec.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class RegisterUserTests extends TestBase {

    @Test
    void successfullyRegisterUserTest() {
        RegisterUserRequestModel registerData = new RegisterUserRequestModel();
        registerData.setEmail("eve.holt@reqres.in");
        registerData.setPassword("pistol");

        RegisterUserResponseModel response = step("Register user", () ->
                given(registerUserRequestSpec)
                        .body(registerData)
                        .when()
                        .post("/register")
                        .then()
                        .spec(registerUserResponseSpec)
                        .extract().as(RegisterUserResponseModel.class));
        step("Verify response", () -> {
            assertThat(response.getId()).isEqualTo(4);
            assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
        });
    }

    @Test
    void unsuccessfullyRegisterUserTest() {
        RegisterUserRequestModel regData = new RegisterUserRequestModel();
        regData.setEmail("sydney@fife");

        MissingPasswordResponseModel response = step("Register user without password", () ->
                given(registerUserRequestSpec)
                        .body(regData)
                        .when()
                        .post("/register")
                        .then()
                        .spec(missingPasswordResponseSpec)
                        .extract().as(MissingPasswordResponseModel.class));

        step("Verify response", () ->
                assertThat(response.getError()).isEqualTo("Missing password"));
    }
}