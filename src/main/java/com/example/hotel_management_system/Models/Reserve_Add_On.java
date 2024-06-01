package com.example.hotel_management_system.Models;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@Table(name="reserve_add_on")
@NoArgsConstructor
@AllArgsConstructor
public class Reserve_Add_On {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="book_id")
    private Reservation booking;


    @ManyToOne
    @JoinColumn(name="add_on_id")
    private Add_On add_on_id;

}
