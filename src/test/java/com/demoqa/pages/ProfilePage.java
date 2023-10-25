package com.demoqa.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ProfilePage {

    public static void openUserProfileWithCookies(String userId, String token, String expires) {

        open("/images/Toolsqa.jpg");
        getWebDriver().manage().addCookie(new Cookie("userID", userId));
        getWebDriver().manage().addCookie(new Cookie("token", token));
        getWebDriver().manage().addCookie(new Cookie("expires", expires));
    }

    @Step("Check that book has been deleted")
    public static void checkBookDeleted(String emptyList) {
        open("/profile");
        $(".rt-noData").shouldHave(Condition.text(emptyList));
    }
}
