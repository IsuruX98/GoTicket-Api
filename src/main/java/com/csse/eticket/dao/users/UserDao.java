package com.csse.eticket.dao.users;

import lombok.Data;

@Data
public class UserDao {
    private Integer id;
    private String name;
    private String email;
    private String contactNumber;
    private String userRole;

    public UserDao() {
    }

    public UserDao(Integer id, String name, String email, String contactNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
        this.userRole = "user";
    }
}
