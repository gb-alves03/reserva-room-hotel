package br.com.ada.reservala.dto;

import br.com.ada.reservala.domain.Room;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RoomDtoRequest {
        @NotNull(message = "Room number não pode ser nulo")
        @Min(value = 1, message = "Room number não pode ser zero ou negativo")
        Integer roomNumber;

        @NotBlank
        String type;

        @NotNull(message = "Price não pode ser nulo")
        @Min(value = 1, message = "Price não pode ser zero ou negativo")
        Integer price;

        Boolean available;


        public RoomDtoRequest(Room room) {
                this.roomNumber = room.getRoomNumber();
                this.type = room.getType();
                this.price = room.getPrice();
                this.available = room.getAvailable();
        }
}
