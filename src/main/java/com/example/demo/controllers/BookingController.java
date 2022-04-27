package com.example.demo.controllers;

import com.example.demo.model.MovieModel;
import com.example.demo.services.BookingService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value ="/")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }


    //remove this from production !!!!
    @PostMapping(value="/resetDB")
    public void resetDB()
    {
        bookingService.initializeDemoDatabase();
    }


    @GetMapping(value = "/localDateTime")
    public LocalDateTime localDateTime() {
        return LocalDateTime.now();
    }

    @GetMapping(value = "/dateTime")
    public Date currentTime() {
        Date date = new Date();
        return date;
    }


    @GetMapping(value = "/movies/{start}/{end}")
    public List<MovieModel> moviesInTimeInterval(@PathVariable LocalDateTime start, @PathVariable LocalDateTime end)
    {
        return bookingService.moviesInTimeInterval(start,end);
    }




}
