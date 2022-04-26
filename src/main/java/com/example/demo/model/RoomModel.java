package com.example.demo.model;

import java.util.List;

public class RoomModel {

    private Long id;
    private Long number;
    private List<SeatModel> seats;

    RoomModel() {
    }

    public RoomModel(Long id, Long number, List<SeatModel> seats) {
        this.id = id;
        this.number = number;
        this.seats = seats;
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

    public List<SeatModel> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatModel> seats) {
        this.seats = seats;
    }
}
