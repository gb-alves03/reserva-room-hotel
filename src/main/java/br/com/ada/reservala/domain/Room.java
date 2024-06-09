package br.com.ada.reservala.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;



public class Room {

    @NotNull(message = "Room number não pode ser nulo")
    @Min(value = 1, message = "Room number não pode ser zero ou negativo")
    private Integer roomNumber;

    @NotBlank
    private String type;

    @NotNull(message = "Price não pode ser nulo")
    @Min(value = 1, message = "Price não pode ser zero ou negativo")
    private Double price;

    @NotNull
    private Boolean available;

    public Room() {
    }

    public Room(Integer roomNumber, String type, Double price, Boolean available) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
        this.available = available;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Room [roomNumber= " + getRoomNumber() + ", type= " + getType() + ", price= " + getPrice() + ", available= " + getAvailable() + "]";
    }
}
