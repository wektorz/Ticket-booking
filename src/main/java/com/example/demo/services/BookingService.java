package com.example.demo.services;


import com.example.demo.entity.MovieEntity;
import com.example.demo.entity.ReservationEntity;
import com.example.demo.entity.RoomEntity;
import com.example.demo.entity.SeatEntity;
import com.example.demo.mapper.MovieMapper;
import com.example.demo.mapper.ReservationMapper;
import com.example.demo.mapper.RoomMapper;
import com.example.demo.mapper.SeatMapper;
import com.example.demo.model.*;
import com.example.demo.repositories.MovieRepository;
import com.example.demo.repositories.ReservationRepository;
import com.example.demo.repositories.RoomRepository;
import com.example.demo.repositories.SeatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.core.Pair;

import java.util.*;
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

        movieRepository.deleteAll();
        reservationRepository.deleteAll();
        roomRepository.deleteAll();
        seatRepository.deleteAll();

        SeatEntity seat1room1 = seatRepository.save(new SeatEntity(1L, 1L, 1L));
        SeatEntity seat2room1 = seatRepository.save(new SeatEntity(2L, 1L, 2L));
        SeatEntity seat3room1 = seatRepository.save(new SeatEntity(3L, 1L, 3L));
        SeatEntity seat4room1 = seatRepository.save(new SeatEntity(4L, 2L, 1L));
        SeatEntity seat5room1 = seatRepository.save(new SeatEntity(5L, 2L, 2L));
        SeatEntity seat6room1 = seatRepository.save(new SeatEntity(6L, 2L, 3L));
        SeatEntity seat7room1 = seatRepository.save(new SeatEntity(7L, 2L, 4L));

        SeatEntity seat1room2 = seatRepository.save(new SeatEntity(8L, 1L, 1L));
        SeatEntity seat2room2 = seatRepository.save(new SeatEntity(9L, 1L, 2L));
        SeatEntity seat3room2 = seatRepository.save(new SeatEntity(10L, 1L, 3L));
        SeatEntity seat4room2 = seatRepository.save(new SeatEntity(11L, 2L, 1L));
        SeatEntity seat5room2 = seatRepository.save(new SeatEntity(12L, 2L, 2L));
        SeatEntity seat6room2 = seatRepository.save(new SeatEntity(13L, 2L, 3L));
        SeatEntity seat7room2 = seatRepository.save(new SeatEntity(14L, 2L, 4L));


        RoomEntity room1 = roomRepository.save(
                new RoomEntity(
                        15L,
                        1L,
                        new ArrayList<SeatEntity>(
                                List.of(seat1room1, seat2room1, seat3room1, seat4room1, seat5room1, seat6room1, seat7room1)
                        )
                )
        );

        RoomEntity room2 = roomRepository.save(
                new RoomEntity(
                        16L,
                        2L,
                        new ArrayList<SeatEntity>(
                                List.of(seat1room2, seat2room2, seat3room2, seat4room2, seat5room2, seat6room2, seat7room2)
                        )
                )
        );

        // empty room without seats for tests
        RoomEntity room3 = roomRepository.save(new RoomEntity(17L, 2L, new ArrayList<SeatEntity>(List.of())));


        ReservationEntity reservation1 = reservationRepository.save(
                new ReservationEntity(18L, "Jan", "Tracz", seat1room1.getId())
        );

        ReservationEntity reservation2 = reservationRepository.save(
                new ReservationEntity(19L, "Anna", "Kowaluk-Łysoń", seat4room1.getId())
        );

        ReservationEntity reservation3 = reservationRepository.save(
                new ReservationEntity(20L, "Michau", "Białek", seat5room2.getId())
        );


        Date date1 = new Date(1651064400000L);
        Date date2 = new Date(1651072500000L);
        Date date3 = new Date(1651245300000L);
        Date date4 = new Date(1651256100000L);

        MovieEntity movie1 = movieRepository.save(
                new MovieEntity(21L, "Wyścig z czasem", date1, date2, room1,
                        new ArrayList<ReservationEntity>(List.of(reservation1, reservation2)))
        );
        MovieEntity movie2 = movieRepository.save(
                new MovieEntity(22L, "Jaś Fasola", date3, date4, room1,
                        new ArrayList<ReservationEntity>(List.of()))
        );
        MovieEntity movie3 = movieRepository.save(
                new MovieEntity(23L, "Łasuch", date1, date2, room2,
                        new ArrayList<ReservationEntity>(List.of(reservation3)))
        );
        MovieEntity movie4 = movieRepository.save(
                new MovieEntity(24L, "Łasuch", date1, date2, room3,
                        new ArrayList<ReservationEntity>(List.of()))
        );


    }

    public List<MovieModel> moviesInTimeInterval(Date start, Date end) {

        List<MovieModel> list = movieMapper.toModel(
                        movieRepository.findByStartBeforeAndEndAfterOrderByStart(end, start))
                .stream()
                .map(Optional<MovieModel>::get)
                .collect(Collectors.toList());

        return list;
    }


    public Optional<MovieDetails> movieDetails(Long id) {
        MovieModel movie = movieMapper.toModel(movieRepository.findById(id).orElseThrow()).orElseThrow();

        List<SeatModel> allSeats = movie.getRoom().getSeats();

        List<SeatModel> availableSeats = allSeats.stream()
                .filter(item -> !movie.getReservations().contains(item.getId()))
                .collect(Collectors.toList());

        return Optional.of(new MovieDetails(movie, availableSeats));
    }


    private List<SeatEntity> deepCopySeatList(List<SeatEntity> s) {
        ArrayList<SeatEntity> result = new ArrayList<>();
        for (SeatEntity seat : s) {
            result.add(new SeatEntity(seat));
        }
        return result;
    }

    public Optional<ReservationResponseModel> makeReservation(ReservationRequestModel request) {

        if (!request.validIdTicketTypes()) {
            throw new IllegalArgumentException("Invalid ticket types");
        }

        List<Long> sentSeatIds = request.getSeats().stream().map(Pair::getFirst).collect(Collectors.toList());
        if (sentSeatIds.size() == 0) {
            throw new IllegalArgumentException("0 seats selected");
        }

        MovieEntity movie = movieRepository.findById(request.getMovieId()).orElseThrow();

        List<Long> reservedSeatIds = movie.getReservations().stream()
                .map(ReservationEntity::getSeatId).collect(Collectors.toList());

        List<SeatEntity> availableSeats = movie.getRoom().getSeats().stream()
                .filter(item -> !reservedSeatIds.contains(item.getId()))
                .collect(Collectors.toList());

        List<Long> availableSeatIds = availableSeats.stream().map(SeatEntity::getId).collect(Collectors.toList());


        // check if desired seats are empty
        for (Pair<Long, String> seat : request.getSeats()) {
            if (!availableSeatIds.contains(seat.getFirst())) {
                throw new IllegalArgumentException("Seat is already taken");
            }
        }


        List<Long> seatsFromReservation =
                request.getSeats().stream()
                        .map(Pair<Long, String>::getFirst)
                        .collect(Collectors.toList());

        // free after reservation
        availableSeatIds.removeAll(seatsFromReservation);


        //check if there exists a singular free seat between reserved seats

        List<SeatEntity> sortedAllSeats = deepCopySeatList(movie.getRoom().getSeats());
        Collections.sort(sortedAllSeats, new SortSeatEntity());

        //all free seats have null id, taken have not null
        sortedAllSeats.stream().map(item -> {
            if (availableSeatIds.contains(item.getId())) item.setId(null);
            return item;
        }).collect(Collectors.toList());


        for (int i = 1; i < sortedAllSeats.size() - 1; i++) {
            SeatEntity left = sortedAllSeats.get(i - 1);
            SeatEntity center = sortedAllSeats.get(i);
            SeatEntity right = sortedAllSeats.get(i + 1);

            //empty seat between reserved on sorted list, need to check rowNumber and positionInRow
            if (center.getId() == null && right.getId() != null && left.getId() != null) {
                if (Objects.equals(left.getRowNumber(), center.getRowNumber())
                        && Objects.equals(center.getRowNumber(), right.getRowNumber())) {
                    if (left.getPositionInRow() + 1 == center.getPositionInRow()
                            && right.getPositionInRow() - 1 == center.getPositionInRow()) {
                        throw new IllegalArgumentException(" single leftover seat");
                    }
                }
            }
        }

        //checks passed init entity and save
        for (Long id : seatsFromReservation) {
            ReservationEntity reservationEntity = reservationRepository.save(
                    new ReservationEntity(request.getName(), request.getSurname(), id));
            movie.getReservations().add(reservationEntity);
        }
        movieRepository.save(movie);


        Double price = request.toPrice();
        // I will suppose that reservation expiration time is week before movie screening.
        Date expirationDate = new Date(movie.getStart().getTime() - 7 * 24 * 60 * 60 * 1000);

        return Optional.of(new ReservationResponseModel(price, expirationDate));
    }

    private static class SortSeatEntity implements Comparator<SeatEntity> {

        public int compare(SeatEntity a, SeatEntity b) {
            return (int) (b.getRowNumber() - a.getRowNumber()
                    + ((b.getRowNumber() - a.getRowNumber() == 0) ? b.getPositionInRow() - a.getPositionInRow() : 0));
        }
    }
}


