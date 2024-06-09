package br.com.ada.reservala.controller;

import br.com.ada.reservala.domain.Room;
import br.com.ada.reservala.repository.RoomRepository;
import br.com.ada.reservala.service.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.sql.DataSource;

class RoomControllerTest {

    @BeforeEach
    public void setUp() {
//       roomController = new RoomController(new RoomService(new RoomRepository(new JdbcTemplate())));
    }

    @Test
    void deveriaCriarRoomNaoNull() {

        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        Room room = new Room(12, "deluxe", 450.0, true);


        RoomRepository roomRepository = new RoomRepository(jdbcTemplate);
        RoomService roomService = new RoomService(roomRepository);

        RoomController roomController = new RoomController(roomService);

        ResponseEntity<Room> response = roomController.createRoom(room);

        assertEquals(200, response.getStatusCodeValue());


    }

}