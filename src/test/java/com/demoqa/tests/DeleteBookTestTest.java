package com.demoqa.tests;

import com.demoqa.models.AddBooksRequestModel;
import com.demoqa.models.DeleteBookRequestModel;
import com.demoqa.models.ISBNModel;
import com.demoqa.models.LoginResponseModel;
import com.demoqa.pages.ProfilePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.demoqa.tests.TestData.credentials;
import static com.demoqa.tests.TestData.getISBN;

public class DeleteBookTestTest extends TestBase {
    @Test
    @DisplayName("Delete book")
    void deleteBookFromProfileTest() {
        LoginResponseModel loginResponse = authorizationApi.login(credentials);

        ISBNModel isbnModel = booksApi.createIsbnModel(getISBN());
        AddBooksRequestModel booksList = booksApi.createAddBooksListModel(loginResponse, isbnModel);
        DeleteBookRequestModel deleteBookRequestModel = booksApi.createDeleteBookModel(loginResponse, isbnModel);

        booksApi.deleteAllBooks(loginResponse);
        booksApi.addBook(loginResponse, booksList);
        booksApi.deleteOneBook(loginResponse, deleteBookRequestModel);

        ProfilePage.openUserProfileWithCookies
                (loginResponse.getUserId(), loginResponse.getToken(), loginResponse.getExpires());
        ProfilePage.checkBookDeleted("No rows found");
    }
}
