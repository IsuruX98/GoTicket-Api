package com.csse.eticket.model;

import com.csse.eticket.model.topup.TopUpAcc;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "recharge")
public class Recharge implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recharge_id")
    private Integer id;

    @Column(name = "amount")
    private float amount;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "time")
    private LocalTime time;

    @OneToOne
    @JoinColumn(name = "topup_id")
    private TopUpAcc topUpAcc;
}
