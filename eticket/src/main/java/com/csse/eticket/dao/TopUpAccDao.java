package com.csse.eticket.dao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class TopUpAccDao {
    private Integer id;

    private String type;

    private float balance;

    private String familyMembers;
}
