package com.example.demo.model;

import com.example.demo.core.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReservationRequestModel {


    private Long movieId; 
    private String name;
    private String surname;
    // (seatId,ticketType)
    private List<Pair<Long, String>> seats;

    ReservationRequestModel() {
    }

    public ReservationRequestModel(Long movieId, String name, String surname, List<Pair<Long, String>> seats) {
        this.movieId = movieId;
        this.name = name;
        this.surname = surname;
        this.seats = seats;
    }

    public boolean validIdTicketTypes() {
        List<String> allowed = new ArrayList<>(List.of("adult", "student", "child"));

        for (Pair<Long, String> seat : seats) {
            if (!allowed.contains(seat.getSecond())) {
                return false;
            }
        }
        return true;
    }

    public Double toPrice() {
        HashMap<String, Long> countMap = new HashMap<String, Long>();
        countMap.put("adult", 0L);
        countMap.put("student", 0L);
        countMap.put("child", 0L);

        for (Pair<Long, String> seat : seats) {
            countMap.put(seat.getSecond(), countMap.get(seat.getSecond()) + 1);
        }

        return  countMap.get("adult") * 25 + countMap.get("student") *18 + countMap.get("child") * 12.50;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
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

    public List<Pair<Long, String>> getSeats() {
        return seats;
    }

    public void setSeats(List<Pair<Long, String>> seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "ReservationRequestModel{" +
                "movieId=" + movieId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", seats=" + seats +
                '}';
    }
}
