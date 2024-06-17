package br.com.ada.reservala.repository;

import br.com.ada.reservala.domain.Room;

import br.com.ada.reservala.exception.RoomNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class RoomRepository {

    private final JdbcTemplate jdbcTemplate;

    private String createSQL = "insert into room(roomNumber, type, price, available) values (?, ?, ?, ?)";
    private String readSQL = "select * from room";
    private String readByRoomNumberSQL = "select * from room where roomNumber = ?";
    private String updateSQL = "update room set type = ?, price = ?, available = ? where roomNumber = ?";
    private String deleteSQL = "delete from room WHERE roomNumber = ?";
    private String deleteAllSQL = "DELETE FROM room";

    public RoomRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Room createRoom(Room room){
        jdbcTemplate.update(
                createSQL,
                room.getRoomNumber(),
                room.getType(),
                room.getPrice(),
                room.getAvailable()
        );
        return room;
    }

    public List<Room> readRoom(){
        RowMapper<Room> rowMapper = ((rs, rowNum) -> new Room(
                rs.getInt("roomNumber"),
                rs.getString("type"),
                rs.getInt("price"),
                rs.getBoolean("available")
        ));
        return jdbcTemplate.query(readSQL, rowMapper);
    }

    public Room readRoomByRoomNumber(Integer roomNumber) {
        RowMapper<Room> rowMapper = ((rs, rowNum) -> new Room(
                rs.getInt("roomNumber"),
                rs.getString("type"),
                rs.getInt("price"),
                rs.getBoolean("available")
        ));
        return jdbcTemplate.queryForObject(readByRoomNumberSQL, rowMapper, roomNumber);
    }

    public Room updateRoom(Room room) {
        int rowsAffected = jdbcTemplate.update(updateSQL,
                room.getType(),
                room.getPrice(),
                room.getAvailable(),
                room.getRoomNumber()
        );
        if (rowsAffected == 0) {
            throw new RoomNotFoundException("Room com número " + room.getRoomNumber() + " não encontrada");
        }
        return room;
    }

    public boolean deleteRoom(Integer roomNumber) {
        int rowsAffected = jdbcTemplate.update(deleteSQL, roomNumber);

        return rowsAffected > 0;
    }

    public int deleteAllRooms() {
        return jdbcTemplate.update(deleteAllSQL);
    }


}
