package com.example.hotel_management_system.Models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@Table(name="room_type_bed")
@NoArgsConstructor
@AllArgsConstructor
public class Room_Type_Bed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;


    @ManyToOne
    @JoinColumn(name="type_id")
    private Room_Type type_id;


    @ManyToOne
    @JoinColumn(name="bed_id")
        private Bed_Type bed_id;

    @Column(name="num_beds")
    private int num_beds;


}
