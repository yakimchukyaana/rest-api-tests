package com.demoqa.tests;

import com.demoqa.models.CredentialsModel;

public class TestData {

    private static final String LOGIN = "shrek";
    private static final String PASSWORD = "Shrek123!";
    private static final String ISBN = "9781449365035";

    public static CredentialsModel credentials = new CredentialsModel(LOGIN, PASSWORD);

    public static String getISBN() {
        return ISBN;
    }

}
