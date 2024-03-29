package com.example.demo.controllers;

import com.example.demo.core.Pair;
import com.example.demo.model.MovieDetails;
import com.example.demo.model.MovieModel;
import com.example.demo.model.ReservationRequestModel;
import com.example.demo.model.ReservationResponseModel;
import com.example.demo.services.BookingService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(value = "/")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }


    //remove this from production !!!!
    @PostMapping(value = "/resetDB")
    public void resetDB() {
        bookingService.initializeDemoDatabase();
    }


    //  The user selects the day and the time when he/she would like to see the movie.
    //The system lists movies available in the given time interval - title and screening
    //times.

    //Timestamps in milliseconds
    @GetMapping(value = "/movies/{startUnixTimestamp}/{endUnixTimestamp}")
    public List<MovieModel> moviesInTimeInterval(@PathVariable Long startUnixTimestamp, @PathVariable Long endUnixTimestamp) {
        return bookingService.moviesInTimeInterval(new Date(startUnixTimestamp), new Date(endUnixTimestamp));
    }


    // The user chooses a particular screening.
    // The system gives information regarding screening room and available seats.

    @GetMapping(value = "/movieDetails/{id}")
    public Optional<MovieDetails> movieDetails(@PathVariable Long id) {
        return bookingService.movieDetails(id);
    }


    // The user chooses seats, and gives the name of the person doing the reservation
    //        (name and surname).
    // The system gives back the total amount to pay and reservation expiration time.

    @PostMapping(value = "/makeReservation/")
    public Optional<ReservationResponseModel> makeReservation(@RequestBody ReservationRequestModel request) {
        return bookingService.makeReservation(request);
    }

    @GetMapping(value = "/test")
    public ReservationRequestModel test() {
        return new ReservationRequestModel(19L, "xd", "januek",
                new ArrayList<>(List.of(new Pair<>(1L, "adult"))));
    }


}
