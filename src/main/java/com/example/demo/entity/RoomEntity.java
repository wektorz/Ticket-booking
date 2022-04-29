package com.example.demo.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ROOM")
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
            @JoinColumn(name = "SEATS_ID", referencedColumnName = "ID")
    )
    private List<SeatEntity> seats;

    public RoomEntity(Long number, List<SeatEntity> seats) {
        this.number = number;
        this.seats = seats;
    }

    public RoomEntity(Long id, Long number, List<SeatEntity> seats) {
        this.id = id;
        this.number = number;
        this.seats = seats;
    }

    public RoomEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public List<SeatEntity> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatEntity> seats) {
        this.seats = seats;
    }
}
