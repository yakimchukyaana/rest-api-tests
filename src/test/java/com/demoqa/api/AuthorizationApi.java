package com.demoqa.api;

import com.demoqa.models.CredentialsModel;
import com.demoqa.models.LoginResponseModel;
import io.qameta.allure.Step;

import static com.demoqa.specs.Spec.logInResponse200Spec;
import static com.demoqa.specs.Spec.requestSpec;
import static io.restassured.RestAssured.given;

public class AuthorizationApi {

    @Step("Authorization request")
    public LoginResponseModel login(CredentialsModel credentials) {
        return given(requestSpec)
                .body(credentials)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(logInResponse200Spec)
                .extract().as(LoginResponseModel.class);
    }
}
