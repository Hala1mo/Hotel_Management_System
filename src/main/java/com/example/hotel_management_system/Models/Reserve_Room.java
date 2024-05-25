package com.example.hotel_management_system.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="reserve_room")
public class Reserve_Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;


    @ManyToOne
    @JoinColumn(name="booking_id")
    private Reservation booking_id;


    @ManyToOne
    @JoinColumn(name="room_id")
    private Room room_id;

}
