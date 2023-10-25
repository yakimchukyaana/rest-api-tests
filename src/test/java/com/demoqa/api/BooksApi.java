package com.demoqa.api;

import com.demoqa.models.*;
import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.List;

import static com.demoqa.specs.Spec.*;
import static io.restassured.RestAssured.given;

public class BooksApi {
    @Step("Delete all books request")
    public void deleteAllBooks(LoginResponseModel loginResponse) {
        given(requestSpec)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .queryParam("UserId", loginResponse.getUserId())
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(deleteBookResponse204Spec);
    }

    @Step("Add book request")
    public void addBook(LoginResponseModel loginResponse, AddBooksRequestModel booksList) {
        given(requestSpec)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .body(booksList)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(addBookResponse201Spec)
                .extract().as(AddBooksResponseModel.class);
    }

    @Step("Delete 1 book request")
    public void deleteOneBook(LoginResponseModel loginResponse, DeleteBookRequestModel deleteBookRequestModel) {
        given(requestSpec)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .queryParam("UserId", loginResponse.getUserId())
                .body(deleteBookRequestModel)
                .when()
                .delete("/BookStore/v1/Book")
                .then()
                .spec(deleteBookResponse204Spec);
    }

    public ISBNModel createIsbnModel(String isbn) {
        ISBNModel isbnModel = new ISBNModel();
        isbnModel.setIsbn(isbn);
        return isbnModel;
    }

    public AddBooksRequestModel createAddBooksListModel(LoginResponseModel loginResponse, ISBNModel isbnModel) {
        AddBooksRequestModel booksList = new AddBooksRequestModel();
        booksList.setUserId(loginResponse.getUserId());
        List<ISBNModel> isbnList = new ArrayList<>();
        isbnList.add(isbnModel);
        booksList.setCollectionOfIsbns(isbnList);
        return booksList;
    }

    public DeleteBookRequestModel createDeleteBookModel(LoginResponseModel loginResponse, ISBNModel isbnModel) {
        DeleteBookRequestModel deleteBookModel = new DeleteBookRequestModel();
        deleteBookModel.setUserId(loginResponse.getUserId());
        deleteBookModel.setIsbn(isbnModel.getIsbn());
        return deleteBookModel;
    }
}
