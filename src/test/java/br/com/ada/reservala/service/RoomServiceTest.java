package br.com.ada.reservala.service;

import br.com.ada.reservala.domain.Room;
import br.com.ada.reservala.repository.RoomRepository;
import org.junit.jupiter.api.Assertions;
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
    void deveriaCriarRoomNaoNull() {
        Room room = new Room(1, "Deluxe", 450.0, true);
        when(roomRepository.createRoom(any(Room.class))).thenReturn(room);

        Room createdRoom = roomService.createRoom(room);

        verify(roomRepository).createRoom(eq(room));

        assertNotNull(createdRoom);
    }

    @Test
    void deveriaNaoCriarRoomComRoomNumberNegativo() {
        Room room = new Room(-4, "Deluxe", 450.0, true);
        when(roomRepository.createRoom(any(Room.class))).thenReturn(room);

        Room createdRoom = roomService.createRoom(room);

        verify(roomRepository).createRoom(eq(room));
        assertTrue(createdRoom.getRoomNumber() > -1);
    }

    @Test
    void deveriaNaoCriarRoomComPriceNegativo() {
        Room room = new Room(4, "Deluxe", -450.0, true);
        when(roomRepository.createRoom(any(Room.class))).thenReturn(room);

        Room createdRoom = roomService.createRoom(room);

        verify(roomRepository).createRoom(eq(room));
        assertTrue(createdRoom.getPrice() > -1);
    }

    @Test
    void deveriaCriarRoomComPriceValoresDouble() {
        Room room = new Room(4, "Deluxe", 450.0, true);
        when(roomRepository.createRoom(any(Room.class))).thenReturn(room);

        Room createdRoom = roomService.createRoom(room);

        verify(roomRepository).createRoom(eq(room));
        assertSame(createdRoom.getPrice().getClass(), Double.class);
    }

    @Test
    void deveriaNaoCriarRoomComAvailableIgualNull() {
        Room room = new Room(4, "Deluxe", 450.0, null);
        when(roomRepository.createRoom(any(Room.class))).thenReturn(room);

        Room createdRoom = roomService.createRoom(room);

        verify(roomRepository).createRoom(eq(room));
        assertNotNull(createdRoom.getAvailable());
    }

    @Test
    void deveriaNaoCriarRoomComTypeIgualNull() {
        Room room = new Room(4, null, 450.0, false);
        when(roomRepository.createRoom(any(Room.class))).thenReturn(room);

        Room createdRoom = roomService.createRoom(room);

        verify(roomRepository).createRoom(eq(room));
        assertNotNull(createdRoom.getType());
    }

    @Test
    void deveriaNaoCriarRoomComTypeIgualNumero() {
        Room room = new Room(4, "132", 450.0, false);
        when(roomRepository.createRoom(any(Room.class))).thenReturn(room);

        Room createdRoom = roomService.createRoom(room);

        verify(roomRepository).createRoom(eq(room));
        assertTrue(createdRoom.getType().matches("\\D+"));
    }



}