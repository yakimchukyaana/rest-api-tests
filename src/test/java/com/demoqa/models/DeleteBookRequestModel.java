package com.demoqa.models;

import lombok.Data;

@Data
public class DeleteBookRequestModel {
    String isbn;
    String userId;
}
