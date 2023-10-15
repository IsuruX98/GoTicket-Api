package com.csse.eticket.dao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopUpAccDao {
    private String type;

    private float balance;

    private String familyMembers;
}
