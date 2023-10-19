package com.csse.eticket.model.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Foreigner extends User {
    @Column(name = "passport_no")
    private String passportNo;
}
