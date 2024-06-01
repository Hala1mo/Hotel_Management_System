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
@Table(name="add_on")
public class Add_On {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name="add_on_name",nullable = false)
    private String add_on_name;

    @Column(name="price",nullable = false)
    private double price;

    @OneToMany(mappedBy = "add_on_id", cascade = {CascadeType.ALL})
    private List<Reserve_Add_On> add_on_id;
}
