package com.example.demo.entity;


import javax.persistence.*;


@Entity
@Table(name = "SEATS")
public class SeatEntity {
    @Id
    @GeneratedValue
    private Long id;

    private Long rowNumber; //fun fact do not name members 'row' or other possible sql keywords, took a while to realize it
    private Long positionInRow;


    public SeatEntity() {
    }

    public SeatEntity(Long rowNumber, Long positionInRow) {
        this.rowNumber = rowNumber;
        this.positionInRow = positionInRow;
    }

    public SeatEntity(Long id, Long rowNumber, Long positionInRow) {
        this.id = id;
        this.rowNumber = rowNumber;
        this.positionInRow = positionInRow;
    }

    public SeatEntity(SeatEntity s)
    {
        this.id = s.id;
        this.rowNumber = s.rowNumber;
        this.positionInRow = s.positionInRow;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPositionInRow() {
        return positionInRow;
    }

    public void setPositionInRow(Long positionInRow) {
        this.positionInRow = positionInRow;
    }

    public Long getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Long rowNumber) {
        this.rowNumber = rowNumber;
    }

}
