package com.example.hotel_management_system.Models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@Table(name="room_type_features")
@NoArgsConstructor
@AllArgsConstructor
public class Room_Type_Feature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;


    @ManyToOne
    @JoinColumn(name="type_id")
    private Room_Type type_id;


    @ManyToOne
    @JoinColumn(name="feature_id")
    private Features feature_id;

}
