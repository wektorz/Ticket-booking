package com.example.demo.model;

import java.util.List;

public class MovieDetails {

    private MovieModel movie;
    private List<SeatModel> availableSeats;

    public MovieDetails() {
    }


    public MovieDetails(MovieModel movie, List<SeatModel> availableSeats) {
        this.movie = movie;
        this.availableSeats = availableSeats;
    }

    public MovieModel getMovie() {
        return movie;
    }

    public void setMovie(MovieModel movie) {
        this.movie = movie;
    }

    public List<SeatModel> getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(List<SeatModel> availableSeats) {
        this.availableSeats = availableSeats;
    }

    @Override
    public String toString() {
        return "MovieDetails{" +
                "movie=" + movie +
                ", availableSeats=" + availableSeats +
                '}';
    }
}
