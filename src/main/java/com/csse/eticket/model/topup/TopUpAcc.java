package com.csse.eticket.model.topup;

import com.csse.eticket.model.users.User;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Inheritance(strategy = InheritanceType.JOINED)
public class TopUpAcc implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topup_id")
    private Integer id;

    @Column(name = "type")
    private String type;

    @Column(name = "balance")
    private float balance;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
