package com.example.hotel_management_system.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "booking_id")
    private Reservation booking;

    @JoinColumn(name = "total_price")
    private double price;



}
