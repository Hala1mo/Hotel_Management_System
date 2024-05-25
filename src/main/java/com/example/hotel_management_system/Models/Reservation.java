package com.example.hotel_management_system.Models;

import com.example.hotel_management_system.Models.Enum.reservationStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="user_id")
    private User user_id;

    @OneToMany(mappedBy = "booking_id", cascade = {CascadeType.ALL})
    private List <Reserve_Room>  booking_room;

    @Column(name="reservation_status",nullable = false)
     private reservationStatus status;

    @Column(name = "num_adults", nullable = false)
    private int num_adults;

    @Column(name = "num_children", nullable = false)
    private int num_children;

    @Column(name = "check_in_date", nullable = false)
    private Date checkInDate;

    @Column(name = "check_out_date", nullable = false)
    private Date checkOutDate;

    @Column(name = "number_of_nights", nullable = false)
    private double number_of_nights;

    @Column(name = "booking_amount", nullable = false)
    private double booking_amount;



}