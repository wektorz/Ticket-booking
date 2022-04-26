package com.example.demo.services;


import com.example.demo.mapper.MovieMapper;
import com.example.demo.mapper.ReservationMapper;
import com.example.demo.mapper.RoomMapper;
import com.example.demo.mapper.SeatMapper;
import com.example.demo.repositories.MovieRepository;
import com.example.demo.repositories.ReservationRepository;
import com.example.demo.repositories.RoomRepository;
import com.example.demo.repositories.SeatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookingService {
    private final MovieRepository movieRepository;
    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private final SeatRepository seatRepository;

    private final MovieMapper movieMapper;
    private final ReservationMapper reservationMapper;
    private final RoomMapper roomMapper;
    private final SeatMapper seatMapper;


    public BookingService(MovieRepository movieRepository, ReservationRepository reservationRepository,
                          RoomRepository roomRepository, SeatRepository seatRepository, MovieMapper movieMapper,
                          ReservationMapper reservationMapper, RoomMapper roomMapper, SeatMapper seatMapper) {
        this.movieRepository = movieRepository;
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
        this.seatRepository = seatRepository;
        this.movieMapper = movieMapper;
        this.reservationMapper = reservationMapper;
        this.roomMapper = roomMapper;
        this.seatMapper = seatMapper;
    }





}
