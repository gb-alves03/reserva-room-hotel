package br.com.ada.reservala.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class Room {

    @NotNull(message = "Room number não pode ser nulo")
    @Min(value = 1, message = "Room number não pode ser zero ou negativo")
    private Integer roomNumber;

    @NotBlank
    private String type;

    @NotNull(message = "Price não pode ser nulo")
    @Min(value = 1, message = "Price não pode ser zero ou negativo")
    private Integer price;
    private Boolean available;

    public Room() {
    }

    public Room(Integer roomNumber, String type, Integer price, Boolean available) {
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
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
