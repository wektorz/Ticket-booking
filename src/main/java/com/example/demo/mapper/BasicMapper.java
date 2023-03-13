package com.example.demo.mapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface BasicMapper<E, M> {
    /* Basic function to map from model to entity */
    Optional<E> toEntity(M model);

    /* Basic function to map from entity to model */
    Optional<M> toModel(E entity);

    /* Default override when arg is a list of models */
    default List<Optional<E>> toEntity(List<M> models) {
        return models.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    /* Default override when arg is a list of entities */
    default List<Optional<M>> toModel(List<E> entities) {
        return entities.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}