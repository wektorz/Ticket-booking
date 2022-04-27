package com.example.demo.repositories;

import com.example.demo.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

    // movies within time interval
    List<MovieEntity> findByStartBeforeAndEndAfter(
            @Param("end") Date startInterval,
            @Param("start") Date endInterval
            );

}
