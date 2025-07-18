package com.company.cloudapp.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserModel {

    private String firstName;
    private String lastName;
    private String pin;
    private String phoneNumber;
}
