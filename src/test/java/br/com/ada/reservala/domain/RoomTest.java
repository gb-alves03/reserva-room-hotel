package br.com.ada.reservala.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

class RoomTest {

    @Test
    void testRoomWithInvalidPrice() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Room invalidRoom = new Room(2, "Standard", -350, true);
        });

        String expectedMessage = "O price não pode ser negativo";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testRoomWithInvalidType() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Room invalidRoom = new Room(2, null, 350, true);
        });

        String expectedMessage = "O type não pode ser vazio";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testRoomWithInvalidRoomNumber() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Room invalidRoom = new Room(-2, "Standard", 350, true);
        });

        String expectedMessage = "O RoomNumber não pode ser negativo ou vazio";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testRoomWithInvalidAvailableValue() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Room invalidRoom = new Room(2, "Standard", 350, null);
        });

        String expectedMessage = "O available não pode ser vazio";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testRoomSetAvailableFalse() {
        Room room = new Room(1, "Standard", 350, true);
        room.setAvailable(false);

        assertFalse(room.getAvailable());
    }

    @Test
    void testRoomAlreadyUnavailable() {
        Room room = new Room(2, "Deluxe", 500, false);

        assertFalse(room.getAvailable());
    }
}
