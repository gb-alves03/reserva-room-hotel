package br.com.ada.reservala.repository;

import br.com.ada.reservala.domain.Room;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomRepository {

    private final JdbcTemplate jdbcTemplate;

    private String createSQL = "insert into room(roomNumber, type, price, avalaible) values (?, ?, ?, ?)";
    private String readSQL = "select * from room";
    private String updateSQL = "update room ";
    private String deleteSQL = "delete from room";

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
                rs.getBoolean("avalaible")
        ));
        return jdbcTemplate.query(readSQL, rowMapper);
    }

    public Room updateRoom(Room room){
        jdbcTemplate.update(updateSQL, room);
        return room;
    }

    public void deleteRoom(Integer roomNumber){
        jdbcTemplate.update(deleteSQL, roomNumber);
    }

}
