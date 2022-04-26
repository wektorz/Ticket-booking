package com.example.demo.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "MOVIES")
public class MovieEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private LocalDateTime start;
    private LocalDateTime end;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private RoomEntity room;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "LCH_RESERVATIONS",
            joinColumns =
            @JoinColumn(name = "MOVIE_ID", referencedColumnName = "ID"),
            inverseJoinColumns =
            @JoinColumn(name = "RESERVATION_ID", referencedColumnName = "ID")
    )
    private List<ReservationEntity> reservations;


}
