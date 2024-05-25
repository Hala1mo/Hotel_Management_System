package com.example.hotel_management_system.Models;

import com.example.hotel_management_system.Models.Enum.roomStatus;
import com.example.hotel_management_system.Models.Enum.roomView;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="rooms")
public class Room  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "room_number")
    private int room_number;

    @Column(name = "floor_number")
    private int floor_number;

    @Enumerated()
    @Column(name = "status", nullable = false)
    private com.example.hotel_management_system.Models.Enum.roomStatus status = roomStatus.AVAILABLE;

    @Enumerated()
    @Column(name = "view", nullable = false)
    private com.example.hotel_management_system.Models.Enum.roomView view = roomView.GARDEN;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="roomType")
    private Room_Type roomType;

    @OneToMany(mappedBy = "room_id", cascade = {CascadeType.ALL})
    private List <Reserve_Room> booking_room;

}

