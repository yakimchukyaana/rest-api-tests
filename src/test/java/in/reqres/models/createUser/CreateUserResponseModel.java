package in.reqres.models.createUser;

import lombok.Data;

@Data
public class CreateUserResponseModel {
    int id;
    String name, job, createdAt;
}
