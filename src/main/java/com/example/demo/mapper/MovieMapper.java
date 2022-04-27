package com.example.demo.mapper;

import com.example.demo.entity.MovieEntity;
import com.example.demo.entity.ReservationEntity;
import com.example.demo.model.MovieModel;
import com.example.demo.model.ReservationModel;
import org.modelmapper.Converter;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MovieMapper implements BasicMapper<MovieEntity, MovieModel> {

    //do not use it to later save, data loss
    @Override
    public Optional<MovieEntity> toEntity(MovieModel model) {

        return Optional.of(new MovieEntity());
    }

    @Override
    public Optional<MovieModel> toModel(MovieEntity entity) {

        if (entity == null) {
            return Optional.empty();
        }

        Converter<List<ReservationEntity>, List<Long>> reservationToSeatId =
                c -> c.getSource().stream().map(item -> item.getSeatId() ).collect(Collectors.toList());

        ModelMapper modelMapper = new ModelMapper();
        TypeMap<MovieEntity, MovieModel> propertyMapper = modelMapper.createTypeMap(MovieEntity.class, MovieModel.class);
        propertyMapper.addMappings(
                mapper -> mapper.using(reservationToSeatId).map(MovieEntity::getReservations,MovieModel::setReservations)
        );

        MovieModel model = modelMapper.map(entity, MovieModel.class);
        Optional<MovieModel> mappedModel = Optional.of(model);

        return mappedModel;
    }
}