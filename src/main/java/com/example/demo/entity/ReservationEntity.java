package com.example.demo.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RESERVATION")
public class ReservationEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String surname;
    private Long seatId;


    public ReservationEntity() {
    }

    public ReservationEntity(String name, String surname, Long seatId ) {
        this.name = name;
        this.surname = surname;
        this.seatId = seatId;
        if( !this.isValid() )
        {
            throw new IllegalArgumentException("Invalid name and surname");
        }
    }

    public ReservationEntity(Long id,String name, String surname, Long seatId ) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.seatId = seatId;
        if( !this.isValid() )
        {
            throw new IllegalArgumentException("Invalid name and surname");
        }
    }

    public boolean isValid() {
        if (name.length() < 3 || surname.length() < 3) return false;

        String nameRegex = "[A-ZŻŹĆĄŚĘŁÓŃ][a-zżźćńółęąś]*";
        String surnameRegex = "[A-ZŻŹĆĄŚĘŁÓŃ][a-zżźćńółęąś]*(-[A-ZŻŹĆĄŚĘŁÓŃ][a-zżźćńółęąś]*)?";

        return name.matches(nameRegex) && surname.matches(surnameRegex);
    }

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
