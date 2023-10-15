package com.csse.eticket.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDAO {

    private int scheduleId;
    private String startTime;
    private String endTime;
    private String date;
    private String busId;
    private String routeName;
}
