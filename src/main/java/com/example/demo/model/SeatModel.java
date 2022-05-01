package com.example.demo.model;

public class SeatModel {
    private Long id;
    private Long rowNumber;
    private Long positionInRow;

    SeatModel() {
    }

    public SeatModel(Long id, Long rowNumber, Long positionInRow) {
        this.id = id;
        this.rowNumber = rowNumber;
        this.positionInRow = positionInRow;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRow() {
        return rowNumber;
    }

    public void setRow(Long rowNumber) {
        this.rowNumber = rowNumber;
    }

    public Long getPositionInRow() {
        return positionInRow;
    }

    public void setPositionInRow(Long positionInRow) {
        this.positionInRow = positionInRow;
    }

    @Override
    public String toString() {
        return "SeatModel{" +
                "id=" + id +
                ", rowNumber=" + rowNumber +
                ", positionInRow=" + positionInRow +
                '}';
    }
}
