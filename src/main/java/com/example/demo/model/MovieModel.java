package com.example.demo.model;

import java.util.Date;
import java.util.List;

public class MovieModel {

    private Long id;
    private String name;
    private Date start;
    private Date end;

    private RoomModel room;
    private List<Long> reservations;

    public MovieModel() {
    }

    public MovieModel(Long id, String name, Date start, Date end, RoomModel room, List<Long> reservations) {
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

    public RoomModel getRoom() {
        return room;
    }

    public void setRoom(RoomModel room) {
        this.room = room;
    }

    public List<Long> getReservations() {
        return reservations;
    }

    public void setReservations(List<Long> reservations) {
        this.reservations = reservations;
    }
}
