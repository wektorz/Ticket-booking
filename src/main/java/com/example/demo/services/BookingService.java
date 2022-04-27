package com.example.demo.services;


import com.example.demo.entity.MovieEntity;
import com.example.demo.entity.ReservationEntity;
import com.example.demo.entity.RoomEntity;
import com.example.demo.entity.SeatEntity;
import com.example.demo.mapper.MovieMapper;
import com.example.demo.mapper.ReservationMapper;
import com.example.demo.mapper.RoomMapper;
import com.example.demo.mapper.SeatMapper;
import com.example.demo.model.MovieDetails;
import com.example.demo.model.MovieModel;
import com.example.demo.model.SeatModel;
import com.example.demo.repositories.MovieRepository;
import com.example.demo.repositories.ReservationRepository;
import com.example.demo.repositories.RoomRepository;
import com.example.demo.repositories.SeatRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public void initializeDemoDatabase() {

        reservationRepository.deleteAll();
        seatRepository.deleteAll();
        roomRepository.deleteAll();
        movieRepository.deleteAll();

        SeatEntity seat1room1 = seatRepository.save(new SeatEntity(1L, 1L));
        SeatEntity seat2room1 = seatRepository.save(new SeatEntity(1L, 2L));
        SeatEntity seat3room1 = seatRepository.save(new SeatEntity(1L, 3L));
        SeatEntity seat4room1 = seatRepository.save(new SeatEntity(2L, 1L));
        SeatEntity seat5room1 = seatRepository.save(new SeatEntity(2L, 2L));
        SeatEntity seat6room1 = seatRepository.save(new SeatEntity(2L, 3L));
        SeatEntity seat7room1 = seatRepository.save(new SeatEntity(2L, 4L));

        SeatEntity seat1room2 = seatRepository.save(new SeatEntity(1L, 1L));
        SeatEntity seat2room2 = seatRepository.save(new SeatEntity(1L, 2L));
        SeatEntity seat3room2 = seatRepository.save(new SeatEntity(1L, 3L));
        SeatEntity seat4room2 = seatRepository.save(new SeatEntity(2L, 1L));
        SeatEntity seat5room2 = seatRepository.save(new SeatEntity(2L, 2L));
        SeatEntity seat6room2 = seatRepository.save(new SeatEntity(2L, 3L));
        SeatEntity seat7room2 = seatRepository.save(new SeatEntity(2L, 4L));


        RoomEntity room1 = roomRepository.save(
                new RoomEntity(
                        1L,
                        new ArrayList<SeatEntity>(
                                List.of(seat1room1, seat2room1, seat3room1, seat4room1, seat5room1, seat6room1, seat7room1)
                        )
                )
        );

        RoomEntity room2 = roomRepository.save(
                new RoomEntity(
                        2L,
                        new ArrayList<SeatEntity>(
                                List.of(seat1room2, seat2room2, seat3room2, seat4room2, seat5room2, seat6room2, seat7room2)
                        )
                )
        );

        // empty room without seats for tests
        RoomEntity room3 = roomRepository.save(new RoomEntity(2L, new ArrayList<SeatEntity>(List.of())));


        ReservationEntity reservation1 = reservationRepository.save(
                new ReservationEntity("Jan", "Tracz", seat1room1.getId())
        );

        ReservationEntity reservation2 = reservationRepository.save(
                new ReservationEntity("Anna", "Kowaluk-Łysoń", seat4room1.getId())
        );

        ReservationEntity reservation3 = reservationRepository.save(
                new ReservationEntity("Michau", "Białek", seat5room2.getId())
        );


        Date date1 = new Date(1651064400000L);
        Date date2 = new Date(1651072500000L);
        Date date3 = new Date(1651245300000L);
        Date date4 = new Date(1651256100000L);

        MovieEntity movie1 = movieRepository.save(
                new MovieEntity("Wyścig z czasem", date1, date2, room1,
                        new ArrayList<ReservationEntity>(List.of(reservation1, reservation2)))
        );
        MovieEntity movie2 = movieRepository.save(
                new MovieEntity("Jaś Fasola", date3, date4, room1,
                        new ArrayList<ReservationEntity>(List.of()))
        );
        MovieEntity movie3 = movieRepository.save(
                new MovieEntity("Łasuch", date1, date2, room2,
                        new ArrayList<ReservationEntity>(List.of(reservation3)))
        );
        MovieEntity movie4 = movieRepository.save(
                new MovieEntity("Łasuch", date1, date2, room3,
                        new ArrayList<ReservationEntity>(List.of()))
        );


    }

    public List<MovieModel> moviesInTimeInterval(Date start, Date end) {

        List<MovieModel> list = movieMapper.toModel(
                movieRepository.findByStartBeforeAndEndAfter(end, start))
                .stream()
                .map(Optional<MovieModel>::get)
                .collect(Collectors.toList());

        return list;
    }

    public Optional<MovieDetails> movieDetails(Long id) {
        MovieModel movie = movieMapper.toModel( movieRepository.findById(id).orElseThrow() ).orElseThrow();

        List<SeatModel> allSeats = movie.getRoom().getSeats();

        List<SeatModel> availableSeats =  allSeats.stream()
                .filter(item -> !movie.getReservations().contains(item.getId()))
                .collect(Collectors.toList());

        return Optional.of(new MovieDetails(movie, availableSeats));
    }


}
