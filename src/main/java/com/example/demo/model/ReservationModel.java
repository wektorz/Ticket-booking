package com.example.demo.model;

public class ReservationModel {
    private Long id;
    private String name;
    private String surname;
    private Long seatId;

    ReservationModel() {
    }

    public ReservationModel(Long id, String name, String surname, Long seatId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.seatId = seatId;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }
}
