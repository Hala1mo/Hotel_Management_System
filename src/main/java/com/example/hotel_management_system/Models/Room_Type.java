package com.example.hotel_management_system.Models;

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
@Table(name="room_type")
public class Room_Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private long id;

    @Column(name="price",nullable = false)
    private double price;

    @Column(name="type_name",nullable = false)
    private String type_name;

    @OneToMany(mappedBy = "roomType", cascade = {CascadeType.ALL})
    private List<Room> roomlist;

    @OneToMany(mappedBy = "type_id", cascade = {CascadeType.ALL})
    private List<Room_Type_Feature> room_type_feature;

    @OneToMany(mappedBy = "type_id", cascade = {CascadeType.ALL})
    private List<Room_Type_Bed> room_type_bed;



}