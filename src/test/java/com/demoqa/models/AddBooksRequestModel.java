package com.demoqa.models;

import lombok.Data;

import java.util.List;

@Data
public class AddBooksRequestModel {
    String userId;
    List<ISBNModel> collectionOfIsbns;
}
