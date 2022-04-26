package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SEAT")
public class SeatEntity {
    @Id
    @GeneratedValue
    private Long id;
    private Long row;
    private Long positionInRow;


}
