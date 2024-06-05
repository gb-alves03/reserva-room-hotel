package br.com.ada.reservala.service;

import br.com.ada.reservala.domain.Room;
import br.com.ada.reservala.repository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoomServiceTest {

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomService roomService;

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
    void testUpdateRoom_Success() {
        Room actualRoom = new Room(2, "Standard", 350, true);
        Room updatedRoom = new Room(2, "Deluxe", 400, false);
        Mockito.when(roomRepository.updateRoom(eq(updatedRoom)))
                .thenReturn(updatedRoom);

        Room result = roomService.updateRoom(updatedRoom);

        assertNotNull(result);
        assertEquals(updatedRoom, result);
        Mockito.verify(roomRepository, times(1)).updateRoom(eq(updatedRoom));
    }

    @Test
    void testUpdateRoom_InvalidValues() {
        Room invalidRoom = new Room(2, "Standard", -350, true);
        assertThrows(IllegalArgumentException.class, () -> {
            roomService.updateRoom(invalidRoom);
        });
        verify(roomRepository, never()).updateRoom(any(Room.class));
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