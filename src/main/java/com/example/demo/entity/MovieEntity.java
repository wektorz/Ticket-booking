package com.example.demo.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "MOVIES")
public class MovieEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Temporal(TemporalType.TIMESTAMP)
    private Date start;
    @Temporal(TemporalType.TIMESTAMP)
    private Date end;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private RoomEntity room;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "MOVIE_RESERVATIONS",
            joinColumns =
            @JoinColumn(name = "MOVIE_ID", referencedColumnName = "ID"),
            inverseJoinColumns =
            @JoinColumn(name = "RESERVATION_ID", referencedColumnName = "ID")
    )
    private List<ReservationEntity> reservations;


    public MovieEntity(String name, Date start, Date end, RoomEntity room, List<ReservationEntity> reservations) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.room = room;
        this.reservations = reservations;
    }

    public MovieEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public RoomEntity getRoom() {
        return room;
    }

    public void setRoom(RoomEntity room) {
        this.room = room;
    }

    public List<ReservationEntity> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationEntity> reservations) {
        this.reservations = reservations;
    }
}
