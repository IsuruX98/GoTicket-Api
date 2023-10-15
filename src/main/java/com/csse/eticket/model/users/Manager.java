package com.csse.eticket.model.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Manager extends User {
    @Column(name = "employee_id")
    private String employeeId;
}
