package br.com.ada.reservala.service;

import br.com.ada.reservala.domain.Room;
import br.com.ada.reservala.repository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@SpringBootTest

public class RoomServiceIntegrationTest {

    @Autowired
    private RoomService roomService;

    @MockBean
    private RoomRepository roomRepository;

    @BeforeEach
    public void setUp() {
        // Inicialização de dois objetos Room
        Room room1 = new Room();
        room1.setRoomNumber(1);
        room1.setType("Standard");
        room1.setPrice(100);
        room1.setAvailable(true);

        Room room2 = new Room();
        room2.setRoomNumber(2);
        room2.setType("Deluxe");
        room2.setPrice(300);
        room2.setAvailable(false);

        Room room3 = new Room();
        room3.setRoomNumber(3);
        room3.setType("Basic");
        room3.setPrice(80);
        room3.setAvailable(true);

        List<Room> rooms = new ArrayList<>();
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);

        when(roomRepository.readRoom()).thenReturn(rooms);
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

        System.out.println(createdRoom.toString());
    }

    @Test
    void readRoom() {
        List<Room> returnedRooms = roomService.readRoom();

        // Verifica o tamanho da lista de quartos retornada
        assertEquals(3, returnedRooms.size(), "O número de quartos deve ser 3");

        // Verifica os detalhes do primeiro quarto
        Room room1 = returnedRooms.get(0);
        System.out.println(room1);
        assertEquals(1, room1.getRoomNumber(), "O número do quarto 1 deve ser 1");
        assertEquals("Standard", room1.getType(), "O tipo do quarto 1 deve ser Standard");
        assertEquals(100, room1.getPrice(), "O preço do quarto 1 deve ser 100");
        assertTrue(room1.isAvailable(), "O quarto 1 deve estar disponível");

        // Verifica os detalhes do segundo quarto
        Room room2 = returnedRooms.get(1);
        System.out.println(room2);

        assertEquals(2, room2.getRoomNumber(), "O número do quarto 2 deve ser 2");
        assertEquals("Deluxe", room2.getType(), "O tipo do quarto 2 deve ser Deluxe");
        assertEquals(300, room2.getPrice(), "O preço do quarto 2 deve ser 300");
        assertFalse(room2.isAvailable(), "O quarto 2 não deve estar disponível");

        // Verifica os detalhes do terceiro quarto
        Room room3 = returnedRooms.get(2);
        System.out.println(room3);
        assertEquals(3, room3.getRoomNumber(), "O número do quarto 3 deve ser 3");
        assertEquals("Basic", room3.getType(), "O tipo do quarto 3 deve ser Basic");
        assertEquals(80, room3.getPrice(), "O preço do quarto 3 deve ser 80");
        assertTrue(room3.isAvailable(), "O quarto 3 deve estar disponível");


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