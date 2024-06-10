package br.com.ada.reservala.service;

import br.com.ada.reservala.domain.Room;
import br.com.ada.reservala.repository.RoomRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

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
    void testCreateRoom_Success() {
        Room room = new Room(1, "Deluxe", 450, true);
        when(roomRepository.createRoom(any(Room.class))).thenReturn(room);

        Room createdRoom = roomService.createRoom(room);

        verify(roomRepository).createRoom(eq(room));

        assertNotNull(createdRoom);
    }

    @Test
    void testCreateRoom_NegativeRoomNumber_Failure() {
        IllegalArgumentException thrown =  Assertions.assertThrows(
                IllegalArgumentException.class,
                ()->  roomService.createRoom(new Room(-12, "Site", 850, false)),
                "Esperava-se que createRoom() fosse lançado, mas isso não aconteceu"
        );
        assertTrue(thrown.getMessage().contains("O Number não pode ser negativo ou vazio"));
    }

    @Test
    void testCreateRoom_NegativePriceValue_Failure() {

        IllegalArgumentException thrown =  Assertions.assertThrows(
                IllegalArgumentException.class,
                ()->  roomService.createRoom(new Room(4, "Deluxe", -450, true)),
                "Esperava-se que createRoom() fosse lançado, mas isso não aconteceu"
        );
        assertTrue(thrown.getMessage().contains("O price não pode ser negativo"));
    }

    @Test
    void testCreateRoom_DoublePriceValue_Failure() {
        Room room = new Room(4, "Deluxe", 450, true);
        when(roomRepository.createRoom(any(Room.class))).thenReturn(room);

        Room createdRoom = roomService.createRoom(room);

        verify(roomRepository).createRoom(eq(room));
        assertSame(createdRoom.getPrice().getClass(), Double.class);
    }


    @Test
    void testCreateRoom_AvailableEqualsToNull_Failure() {

        IllegalArgumentException thrown =  Assertions.assertThrows(
                IllegalArgumentException.class,
                ()->  roomService.createRoom(new Room(4, "Deluxe", 450, null)),
                "Esperava-se que createRoom() fosse lançado, mas isso não aconteceu"
        );
        assertTrue(thrown.getMessage().contains("O availability não pode ser vazio."));
    }

    @Test
    void testCreateRoom_TypeEqualsToNull_Failure() {

        IllegalArgumentException thrown =  Assertions.assertThrows(
                IllegalArgumentException.class,
                ()->  roomService.createRoom(new Room(4, null, 450, false)),
                "Esperava-se que createRoom() fosse lançado, mas isso não aconteceu"
        );
        assertTrue(thrown.getMessage().contains("O type não pode ser vazio ou numerico"));
    }


    @Test
    void testCreateRoom_TypeEqualsToNumber_Failure() {

        IllegalArgumentException thrown =  Assertions.assertThrows(
                IllegalArgumentException.class,
                ()->  roomService.createRoom(new Room(4, "132", 450, false)),
                "Esperava-se que createRoom() fosse lançado, mas isso não aconteceu"
        );
        assertTrue(thrown.getMessage().contains("O type não pode ser vazio ou numerico"));

    }

    @Test
    void testReadRoom_Success() {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(1, "Standard", 100, true));
        rooms.add(new Room(2, "Deluxe", 200, false));

        when(roomRepository.readRoom()).thenReturn(rooms);

        List<Room> returnedRooms = roomService.readRoom();

        assertFalse(returnedRooms.isEmpty());

        for (Room room : returnedRooms) {
            System.out.println(room.toString());
            //   System.out.println("-----------------------");
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
    void testUpdateRoom_InvalidPriceValue() {
        Room invalidRoom = new Room(2, "Standard", -350, true);

        IllegalArgumentException thrown =  Assertions.assertThrows(
                IllegalArgumentException.class,
                ()->  roomService.updateRoom(invalidRoom),
                "Esperava-se que updateRoom() fosse lançado, mas isso não aconteceu"
        );

        assertTrue(thrown.getMessage().contains("O price não pode ser negativo"));
        verify(roomRepository, never()).updateRoom(any(Room.class));
    }

    @Test
    void testUpdateRoom_InvalidTypeValue() {
        Room invalidRoom = new Room(2, null, -350, true);

        IllegalArgumentException thrown =  Assertions.assertThrows(
                IllegalArgumentException.class,
                ()->  roomService.updateRoom(invalidRoom),
                "Esperava-se que updateRoom() fosse lançado, mas isso não aconteceu"
        );

        assertTrue(thrown.getMessage().contains("O type não pode ser vazio ou numerico"));
        verify(roomRepository, never()).updateRoom(any(Room.class));
    }

    @Test
    void testUpdateRoom_InvalidRoomNumber() {
        Room invalidRoom = new Room(1, "Standard", 350, null);

        IllegalArgumentException thrown =  Assertions.assertThrows(
                IllegalArgumentException.class,
                ()->  roomService.updateRoom(invalidRoom),
                "Esperava-se que updateRoom() fosse lançado, mas isso não aconteceu"
        );

        assertTrue(thrown.getMessage().contains("O availability não pode ser vazio"));
        verify(roomRepository, never()).updateRoom(any(Room.class));

    }

    @Test
    void testUpdateRoom_InvalidAvailableValue() {
        Room invalidRoom = new Room(2, "Standard", 350, true);

        IllegalArgumentException thrown =  Assertions.assertThrows(
                IllegalArgumentException.class,
                ()->  roomService.updateRoom(invalidRoom),
                "Esperava-se que updateRoom() fosse lançado, mas isso não aconteceu"
        );

        assertTrue(thrown.getMessage().contains("O type não pode ser vazio ou numerico"));
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