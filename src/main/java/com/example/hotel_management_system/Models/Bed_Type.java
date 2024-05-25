package com.example.hotel_management_system.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="bed_type")
public class Bed_Type{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name="bed_type",nullable = false)
    private String bed_type;

    @OneToMany(mappedBy = "bed_id", cascade = {CascadeType.ALL})
    private List<Room_Type_Bed> room_type_bed;

}
