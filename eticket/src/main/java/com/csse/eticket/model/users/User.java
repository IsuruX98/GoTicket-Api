package com.csse.eticket.model.users;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
@NamedQuery(name = "User.findByEmailId", query = "select u from User u where u.email =:email")
@NamedQuery(name = "User.getAllUser", query = "select new com.csse.eticket.dao.users.UserDao(u.id, u.name, u.email, u.contactNumber, u.role) from User u")
@NamedQuery(name = "User.getAllAdmin", query = "select u.email from User u where u.role='admin'")
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "contactNumber")
    private String contactNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;
}
