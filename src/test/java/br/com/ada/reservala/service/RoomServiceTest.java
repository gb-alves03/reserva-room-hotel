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
        IllegalArgumentException thrown =  Assertions.assertThrows(
                IllegalArgumentException.class,
                ()->  roomService.createRoom(new Room(-12, "Site", 850.78, false)),
                "Esperava-se que createRoom() fosse lançado, mas isso não aconteceu"
        );
        assertTrue(thrown.getMessage().contains("O Number não pode ser negativo ou vazio"));
    }

    @Test
    void deveriaNaoCriarRoomComPriceNegativo() {

        IllegalArgumentException thrown =  Assertions.assertThrows(
                IllegalArgumentException.class,
                ()->  roomService.createRoom(new Room(4, "Deluxe", -450.0, true)),
                "Esperava-se que createRoom() fosse lançado, mas isso não aconteceu"
        );
        assertTrue(thrown.getMessage().contains("O price não pode ser negativo"));
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

        IllegalArgumentException thrown =  Assertions.assertThrows(
                IllegalArgumentException.class,
                ()->  roomService.createRoom(new Room(4, "Deluxe", 450.0, null)),
                "Esperava-se que createRoom() fosse lançado, mas isso não aconteceu"
        );
        assertTrue(thrown.getMessage().contains("O availability não pode ser vazio."));
    }

    @Test
    void deveriaNaoCriarRoomComTypeIgualNull() {

        IllegalArgumentException thrown =  Assertions.assertThrows(
                IllegalArgumentException.class,
                ()->  roomService.createRoom(new Room(4, null, 450.0, false)),
                "Esperava-se que createRoom() fosse lançado, mas isso não aconteceu"
        );
        assertTrue(thrown.getMessage().contains("O type não pode ser vazio ou numerico"));
    }


    @Test
    void deveriaNaoCriarRoomComTypeIgualNumero() {

        IllegalArgumentException thrown =  Assertions.assertThrows(
                IllegalArgumentException.class,
                ()->  roomService.createRoom(new Room(4, "132", 450.0, false)),
                "Esperava-se que createRoom() fosse lançado, mas isso não aconteceu"
        );
        assertTrue(thrown.getMessage().contains("O type não pode ser vazio ou numerico"));

    }



}