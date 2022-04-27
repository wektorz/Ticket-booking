package com.example.demo.repositories;

import com.example.demo.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

    // movies within time interval
    List<MovieEntity> findByStartBeforeAndEndAfter(
            Date startInterval,
            Date endInterval
            );
    Optional<MovieEntity> findById(Long id);

}
