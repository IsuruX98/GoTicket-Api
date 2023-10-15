package com.csse.eticket.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class BusRouteDao {
    private String routeName;
    private String startPoint;
    private String endPoint;
    private double ticketPrice;
}
