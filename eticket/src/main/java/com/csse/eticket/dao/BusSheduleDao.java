package com.csse.eticket.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class BusSheduleDao {
    private Integer scheduleId;
    private String startTime;
    private String endTime;
    private String date;
    private String busNo;
    private String routeName;
}
