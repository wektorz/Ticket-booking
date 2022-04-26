package com.example.demo.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ROOMS")
public class RoomEntity {
    @Id
    @GeneratedValue
    private Long id;
    private Long number;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "ROOM_SEATS",
            joinColumns =
            @JoinColumn(name = "ROOM_ID", referencedColumnName = "ID"),
            inverseJoinColumns =
            @JoinColumn(name = "SEAT_ID", referencedColumnName = "ID")
    )
    private List<SeatEntity> seats;

}
