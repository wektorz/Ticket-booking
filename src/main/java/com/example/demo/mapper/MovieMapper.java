package com.example.demo.mapper;

import com.example.demo.entity.MovieEntity;
import com.example.demo.model.MovieModel;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

import java.util.Optional;

@Component
public class MovieMapper implements BasicMapper<MovieEntity, MovieModel> {

    @Override
    public Optional<MovieEntity> toEntity(MovieModel model) {
        if (model == null) {
            return Optional.empty();
        }

        ModelMapper modelMapper = new ModelMapper();
        MovieEntity entity = modelMapper.map(model, MovieEntity.class);
        Optional<MovieEntity> mappedEntity = Optional.of(entity);

        return mappedEntity;
    }

    @Override
    public Optional<MovieModel> toModel(MovieEntity entity) {

        if (entity == null) {
            return Optional.empty();
        }

        ModelMapper modelMapper = new ModelMapper();
        MovieModel model = modelMapper.map(entity, MovieModel.class);
        Optional<MovieModel> mappedModel = Optional.of(model);

        return mappedModel;
    }
}