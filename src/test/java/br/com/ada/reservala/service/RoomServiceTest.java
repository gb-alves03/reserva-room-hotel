package br.com.ada.reservala.service;

import br.com.ada.reservala.domain.Room;
import br.com.ada.reservala.repository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(1, "Standard", 100, true));
        rooms.add(new Room(2, "Deluxe", 200, false));

        when(roomRepository.readRoom()).thenReturn(rooms);

        List<Room> returnedRooms = roomService.readRoom();

        assertFalse(returnedRooms.isEmpty());

        for (Room room : returnedRooms) {
            System.out.println(room.toString());
            System.out.println("-----------------------");
        }

        assertEquals(2, returnedRooms.size());
        assertEquals(1, returnedRooms.get(0).getRoomNumber());
        assertEquals("Standard", returnedRooms.get(0).getType());
        assertEquals(100, returnedRooms.get(0).getPrice());
        assertTrue(returnedRooms.get(0).isAvailable());
        assertEquals(2, returnedRooms.get(1).getRoomNumber());
        assertEquals("Deluxe", returnedRooms.get(1).getType());
        assertEquals(200, returnedRooms.get(1).getPrice());
        assertFalse(returnedRooms.get(1).isAvailable());

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