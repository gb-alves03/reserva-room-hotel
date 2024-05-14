package br.com.ada.reservala.service;

import br.com.ada.reservala.domain.Room;
import br.com.ada.reservala.repository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RoomServiceTest {
    private RoomService roomService;
    private RoomRepository roomRepository;

    @BeforeEach
    public void setUp() {
        roomRepository = mock(RoomRepository.class);
        roomService = new RoomService(roomRepository);
    }

    @Test
    void createRoom() {
        Room room = new Room();
        room.setRoomNumber(1);
        room.setType("Deluxe");
        room.setPrice(450);
        room.setAvailable(true);

        when(roomRepository.createRoom(any(Room.class))).thenReturn(room);

        Room createdRoom = roomService.createRoom(room);

        verify(roomRepository).createRoom(eq(room));

        assertTrue(createdRoom != null);
        System.out.println(createdRoom.toString());
    }

    @Test
    void readRoom() {
    }

    @Test
    void updateRoom() {
    }

    @Test
    void deleteRoom() {
    }

    @Test
    void getOcupation() {
    }

    @Test
    void getRevenue() {
    }
}