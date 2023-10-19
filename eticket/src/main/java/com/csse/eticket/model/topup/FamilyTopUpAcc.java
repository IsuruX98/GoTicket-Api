package com.csse.eticket.model.topup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FamilyTopUpAcc extends TopUpAcc {
    @Column(name = "child_name")
    private String childName;
}
