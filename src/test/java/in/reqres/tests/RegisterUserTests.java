package in.reqres.tests;

import in.reqres.models.register.MissingPasswordResponseModel;
import in.reqres.models.register.RegisterUserRequestModel;
import in.reqres.models.register.RegisterUserResponseModel;
import org.junit.jupiter.api.Test;

import static in.reqres.specs.RequestSpec.*;
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
                given(requestSpec)
                        .body(registerData)
                        .when()
                        .post("/register")
                        .then()
                        .spec(userResponse200Spec)
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
                given(requestSpec)
                        .body(regData)
                        .when()
                        .post("/register")
                        .then()
                        .spec(missingPasswordResponse400Spec)
                        .extract().as(MissingPasswordResponseModel.class));

        step("Verify response", () ->
                assertThat(response.getError()).isEqualTo("Missing password"));
    }
}