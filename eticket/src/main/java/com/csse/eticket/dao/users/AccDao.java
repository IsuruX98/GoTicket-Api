package com.csse.eticket.dao.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccDao {
    private String name;

    private String contactNumber;

    private String email;

    private String password;

    private String role;

    private String uniqueField;
}
