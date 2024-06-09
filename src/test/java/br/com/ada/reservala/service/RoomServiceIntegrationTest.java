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

        // Adicionando os objetos Room a uma lista
        List<Room> rooms = new ArrayList<>();
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);




        // Configurando o mock do RoomRepository para retornar a lista de Rooms
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

        Room room1 = returnedRooms.get(0);
        Room room2 = returnedRooms.get(1);
        Room room3 = returnedRooms.get(2);

        System.out.println(room1);
        System.out.println(room2);
        System.out.println(room3);

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