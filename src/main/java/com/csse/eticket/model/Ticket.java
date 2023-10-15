package com.csse.eticket.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "ticket")
public class Ticket {
    @Id
    private int id;

    @Column(columnDefinition = "TEXT")
    private String qrData;

}

