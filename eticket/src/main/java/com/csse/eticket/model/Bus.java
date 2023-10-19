package com.csse.eticket.model;

import com.csse.eticket.model.users.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bus")
public class Bus {
    @Id
    @Column(name = "bus_no")
    private String busNo;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "route_name")
    private BusRoute route;

    @Column(name = "income")
    private float income;
}
