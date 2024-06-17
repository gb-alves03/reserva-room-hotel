package br.com.ada.reservala.repository;

import br.com.ada.reservala.domain.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoomRepositoryTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private RoomRepository roomRepository;

    @Captor
    private ArgumentCaptor<Object[]> argsCaptor;

    private Room validRoom;

    @BeforeEach
    void setUp() {
        validRoom = new Room(2, "Standard", 400, true);
    }

    @Test
    void testUpdateRoomSuccess() {
        when(jdbcTemplate.update(anyString(), any(Object[].class))).thenReturn(1);

        Room result = roomRepository.updateRoom(validRoom);

        assertNotNull(result);
        assertEquals(validRoom, result);

        verify(jdbcTemplate, times(1)).update(
                eq("update room set type = ?, price = ?, available = ? where roomNumber = ?"), argsCaptor.capture()
        );

        Object[] args = argsCaptor.getValue();

        assertEquals(validRoom.getType(), args[0]);
        assertEquals(validRoom.getPrice(), args[1]);
        assertEquals(validRoom.getAvailable(), args[2]);
        assertEquals(validRoom.getRoomNumber(), args[3]);
    }

    @Test
    void testUpdateRoomWithNonExistentRoom() {
        when(jdbcTemplate.update(anyString(), any(Object[].class))).thenThrow(new RuntimeException("Room not found"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            roomRepository.updateRoom(validRoom);
        });

        assertEquals("Room not found", exception.getMessage());

        verify(jdbcTemplate, times(1)).update(eq("update room set type = ?, price = ?, available = ? where roomNumber = ?"), any(Object[].class));
    }

    @Test
    void testUpdateRoomWithNegativeRoomNumber() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Room invalidRoom = new Room(-1, "Deluxe", 400, false);
            roomRepository.updateRoom(invalidRoom);
        });

        String expectedMessage = "O RoomNumber não pode ser negativo ou vazio";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testDeleteRoomSuccess() {
        when(jdbcTemplate.update(anyString(), any(Integer.class))).thenReturn(1);

        boolean result = roomRepository.deleteRoom(validRoom.getRoomNumber());

        assertTrue(result);
        verify(jdbcTemplate, times(1)).update(eq("delete from room WHERE roomNumber = ?"), eq(validRoom.getRoomNumber()));
    }

    @Test
    void testDeleteRoomWithNonExistentRoom() {
        when(jdbcTemplate.update(anyString(), any(Integer.class))).thenReturn(0);

        boolean result = roomRepository.deleteRoom(999);

        assertFalse(result);
        verify(jdbcTemplate, times(1)).update(eq("delete from room WHERE roomNumber = ?"), eq(999));
    }

    @Test
    void testDeleteRoomWithException() {
        when(jdbcTemplate.update(anyString(), any(Integer.class))).thenThrow(new DataAccessException("Erro ao excluir quarto: Erro no banco de dados") {});

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            roomRepository.deleteRoom(validRoom.getRoomNumber());
        });

        assertEquals("Erro ao excluir quarto: Erro no banco de dados", exception.getMessage());
        verify(jdbcTemplate, times(1)).update(eq("delete from room WHERE roomNumber = ?"), eq(validRoom.getRoomNumber()));
    }
}
