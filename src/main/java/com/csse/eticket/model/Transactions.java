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

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transactions")
public class Transactions implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Integer id;

    @Column(name = "amount")
    private float amount;

    @Column(name = "type")
    private String type;

    @Column(name = "date")
    private LocalDate date;

    @OneToOne
    @JoinColumn(name = "topup_id")
    private TopUpAcc topUpAcc;
}
