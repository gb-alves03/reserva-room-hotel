package br.com.ada.reservala.service;

import br.com.ada.reservala.domain.Room;
import br.com.ada.reservala.exception.RoomNotFoundException;
import br.com.ada.reservala.repository.RoomRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;

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
    void testCreateRoomSuccess() {
        Room room = new Room(1, "Deluxe", 450, true);
        when(roomRepository.createRoom(any(Room.class))).thenReturn(room);

        Room createdRoom = roomService.createRoom(room);

        verify(roomRepository).createRoom(eq(room));

        assertNotNull(createdRoom);
    }

    @Test
    void testCreateRoomWithNegativeRoomNumber() {
        IllegalArgumentException thrown =  Assertions.assertThrows(
                IllegalArgumentException.class,
                ()->  roomService.createRoom(new Room(-12, "Site", 850, false)),
                "Esperava-se que createRoom() fosse lançado, mas isso não aconteceu"
        );
        assertTrue(thrown.getMessage().contains("O RoomNumber não pode ser negativo ou vazio"));
    }

    @Test
    void testCreateRoomWithNegativePriceValue() {

        IllegalArgumentException thrown =  Assertions.assertThrows(
                IllegalArgumentException.class,
                ()->  roomService.createRoom(new Room(4, "Deluxe", -450, true)),
                "Esperava-se que createRoom() fosse lançado, mas isso não aconteceu"
        );
        assertTrue(thrown.getMessage().contains("O price não pode ser negativo"));
    }


    @Test
    void testCreateRoom_AvailableEqualsToNull_Failure() {

        IllegalArgumentException thrown =  Assertions.assertThrows(
                IllegalArgumentException.class,
                ()->  roomService.createRoom(new Room(4, "Deluxe", 450, null)),
                "Esperava-se que createRoom() fosse lançado, mas isso não aconteceu"
        );
        assertTrue(thrown.getMessage().contains("O available não pode ser vazio"));
    }

    @Test
    void testCreateRoomWithTypeEqualsToNull_Failure() {

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
    void testUpdateRoomSuccess() {
        Room room = new Room(2, "Deluxe", 400, false);
        Mockito.when(roomRepository.updateRoom(eq(room)))
                .thenReturn(room);

        Room updatedRoom = roomService.updateRoom(room);

        assertNotNull(updatedRoom);
        assertEquals(room.getRoomNumber(), updatedRoom.getRoomNumber());
        Mockito.verify(roomRepository, times(1)).updateRoom(eq(room));
    }

    @Test
    void testUpdateRoomWithNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            roomService.updateRoom(null);
        });

        String expectedMessage = "Room não pode ser nulo";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        Mockito.verify(roomRepository, times(0)).updateRoom(any(Room.class));
    }

    @Test
    void testUpdateRoomWithNonExistentRoom() {
        Room room = new Room(850, "Suite", 500, true);

        Mockito.when(roomRepository.updateRoom(room)).thenThrow(new RoomNotFoundException("Room com número " + room.getRoomNumber() + " não encontrada"));

        Exception exception = assertThrows(RoomNotFoundException.class, () -> {
            roomService.updateRoom(room);
        });
        String expctedMessage = "Room com número " + room.getRoomNumber() + " não encontrada";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expctedMessage));
        Mockito.verify(roomRepository, times(1)).updateRoom(room);
    }



    @Test
    void testDeleteRoomSuccess() {
        Integer roomNumber = 1;
        Mockito.when(roomRepository.deleteRoom(roomNumber)).thenReturn(true);

        boolean deleted = roomService.deleteRoom(roomNumber);

        assertTrue(deleted);
        Mockito.verify(roomRepository, times(1)).deleteRoom(roomNumber);
    }

    @Test
    void testDeleteWithNegativeRoomNumber() {
        Integer roomNumber = -1;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            roomService.deleteRoom(roomNumber);
        });

        String expectedMessage = "O número do quarto deve ser positivo.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        Mockito.verify(roomRepository, times(0)).deleteRoom(eq(roomNumber));

    }

    @Test
    void testDeleteRoomWithNonExistentRoom() {
        Room room = new Room(850, "Suite", 500, true);

        Mockito.when(roomRepository.deleteRoom(room.getRoomNumber())).thenThrow(new RoomNotFoundException("Room com número " + room.getRoomNumber() + " não encontrada"));

        Exception exception = assertThrows(RoomNotFoundException.class, () -> {
            roomService.deleteRoom(room.getRoomNumber());
        });
        String expctedMessage = "Room com número " + room.getRoomNumber() + " não encontrada";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expctedMessage));
        Mockito.verify(roomRepository, times(1)).deleteRoom(room.getRoomNumber());
    }

    @Test
    void testDeleteRoomNull() {
        assertThrows(IllegalArgumentException.class, () -> roomService.deleteRoom(null));
        Mockito.verify(roomRepository, never()).deleteRoom(any());
    }

    @Test
    void testDeleteRoomDatabaseFailure() {
        Integer roomNumber = 4;
        doThrow(EmptyResultDataAccessException.class).when(roomRepository).deleteRoom(roomNumber);

        assertThrows(RuntimeException.class, () -> roomService.deleteRoom(roomNumber));
        Mockito.verify(roomRepository, times(1)).deleteRoom(roomNumber);
    }

    @Test
    void testDeleteAllRoomsSuccess() {
        when(roomRepository.deleteAllRooms()).thenReturn(5);
        int deletedCount = roomService.deleteAllRooms();
        assertEquals(5, deletedCount);
        Mockito.verify(roomRepository, times(1)).deleteAllRooms();
    }

    @Test
    void testDeleteAllRoomsFailure() {
        doThrow(EmptyResultDataAccessException.class).when(roomRepository).deleteAllRooms();
        assertThrows(RuntimeException.class, () -> roomService.deleteAllRooms());
        Mockito.verify(roomRepository, times(1)).deleteAllRooms();
    }



    @Test
    void getOcupation() {
    }

    @Test
    void getRevenue() {
    }
}