package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.List;

public class MovieModel {

    private Long id;
    private String name;
    private LocalDateTime start;
    private LocalDateTime end;

    private RoomModel room;
    private List<ReservationModel> reservations;

    public MovieModel() {
    }

    public MovieModel(Long id, String name, LocalDateTime start, LocalDateTime end, RoomModel room, List<ReservationModel> reservations) {
        this.id = id;
        this.name = name;
        this.start = start;
        this.end = end;
        this.room = room;
        this.reservations = reservations;
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

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public RoomModel getRoom() {
        return room;
    }

    public void setRoom(RoomModel room) {
        this.room = room;
    }

    public List<ReservationModel> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationModel> reservations) {
        this.reservations = reservations;
    }
}
