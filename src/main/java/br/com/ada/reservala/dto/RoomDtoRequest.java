package br.com.ada.reservala.dto;


import br.com.ada.reservala.domain.Room;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomDtoRequest {

        @NotNull(message = "Room number não pode ser nulo")
        @Min(value = 1, message = "Room number não pode ser zero ou negativo")
        Integer roomNumber;

        @NotBlank(message = "O type não pode ser vazio")
        @Pattern(regexp = "\\D+", message = "O type não pode ser numérico")
        String type;

        @Positive(message = "O price deve ser positivo")
        Integer price;

        @NotNull(message = "O available não pode ser vazio")
        Boolean available;

        public RoomDtoRequest() {

        }

        public RoomDtoRequest(Integer roomNumber, String type, Integer price, Boolean available) {
                this.roomNumber = roomNumber;
                this.type = type;
                this.price = price;
                this.available = available;
        }

        public Room convertToRoom() {
                Room room = new Room();
                room.setRoomNumber(this.getRoomNumber());
                room.setType(this.getType());
                room.setPrice(this.getPrice());
                room.setAvailable(this.getAvailable());
                return room;
        }

        public RoomDtoRequest convertToRoomDto(Room room) {
                RoomDtoRequest roomDto = new RoomDtoRequest();
                roomDto.setRoomNumber(room.getRoomNumber());
                roomDto.setType(room.getType());
                roomDto.setPrice(room.getPrice());
                roomDto.setAvailable(room.getAvailable());
                return roomDto;
        }
}
