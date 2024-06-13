package br.com.ada.reservala.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RoomTest {

    @Test
    void testUpdateRoom_InvalidPriceValue_Failure() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Room invalidRoom = new Room(2, "Standard", -350, true);
        });

        String expectedMessage = "O price não pode ser negativo";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testUpdateRoom_InvalidTypeValue() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Room invalidRoom = new Room(2, null, 350, true);
        });

        String expectedMessage = "O type não pode ser vazio";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testUpdateRoom_InvalidRoomNumber() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Room invalidRoom = new Room(-2, "Standard", 350, true);
        });

        String expectedMessage = "O RoomNumber não pode ser negativo ou vazio";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testUpdateRoom_InvalidAvailableValue() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Room invalidRoom = new Room(2, "Standard", 350, null);
        });

        String expectedMessage = "O available não pode ser vazio";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testDeleteRoom_Success() {
        Room room = new Room(1, "Standard", 350, true);
        room.setAvailable(false);

        assertFalse(room.getAvailable());
    }

    @Test
    void testDeleteRoom_AlreadyUnavailable() {
        Room room = new Room(2, "Deluxe", 500, false);


        assertFalse(room.getAvailable());
    }

    @Test
    void testDeleteRoom_InvalidRoomNumber() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Room room = new Room(-3, "Standard", 350, true);
            room.setAvailable(false);
        });

        String expectedMessage = "O RoomNumber não pode ser negativo ou vazio";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
