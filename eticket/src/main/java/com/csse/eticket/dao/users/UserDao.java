package com.csse.eticket.dao.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDao {
    private Integer id;
    private String name;
    private String email;
    private String contactNumber;
    private String userRole;

}
