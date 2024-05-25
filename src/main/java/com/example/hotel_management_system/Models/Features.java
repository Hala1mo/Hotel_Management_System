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
@Table(name="room_feature")
public class Features {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private long id;

    @Column(name="feature",nullable = false)
    private String feature;

    @Column(name="description",nullable = false)
    private String description;

    @OneToMany(mappedBy = "feature_id", cascade = {CascadeType.ALL})
    private List<Room_Type_Feature> room_type_feature;

}