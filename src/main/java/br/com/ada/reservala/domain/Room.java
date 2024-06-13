package br.com.ada.reservala.domain;


public class Room {

    private Integer roomNumber;
    private String type;
    private Integer price;
    private Boolean available;

    public boolean isAvailable() {
        return available;
    }

    public Room() {

    }

    public Room(Integer roomNumber, String type, Integer price, Boolean available) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
        this.available = available;
        validate();
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (!roomNumber.equals(room.roomNumber)) return false;
        if (!type.equals(room.type)) return false;
        if (!price.equals(room.price)) return false;
        return available.equals(room.available);
    }

    @Override
    public int hashCode() {
        int result = roomNumber.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + available.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", available=" + available +
                '}';
    }

    public boolean isAvailable() {
        return available;
    }

    private void validate() {
        if (this.getRoomNumber() < 0 || this.getRoomNumber() == null) {
            throw new IllegalArgumentException("O RoomNumber não pode ser negativo ou vazio");
        }
        if (this.getType() == null || !this.getType().matches("\\D+")) {
            throw new IllegalArgumentException("O type não pode ser vazio ou numerico");
        }
        if (this.getPrice() < 0) {
            throw new IllegalArgumentException("O price não pode ser negativo");
        }
        if (this.getAvailable() == null) {
            throw new IllegalArgumentException("O available não pode ser vazio");
        }
    }
}
