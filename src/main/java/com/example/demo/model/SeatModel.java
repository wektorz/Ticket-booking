package com.example.demo.model;

public class SeatModel {
    private Long id;
    private Long row;
    private Long positionInRow;

    SeatModel() {

    }

    public SeatModel(Long id, Long row, Long positionInRow) {
        this.id = id;
        this.row = row;
        this.positionInRow = positionInRow;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRow() {
        return row;
    }

    public void setRow(Long row) {
        this.row = row;
    }

    public Long getPositionInRow() {
        return positionInRow;
    }

    public void setPositionInRow(Long positionInRow) {
        this.positionInRow = positionInRow;
    }
}
