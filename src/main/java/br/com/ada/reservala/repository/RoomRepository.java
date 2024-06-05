package br.com.ada.reservala.repository;

import br.com.ada.reservala.domain.Room;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
public class RoomRepository {

    private final JdbcTemplate jdbcTemplate;

    private String createSQL = "insert into room(roomNumber, type, price, available) values (?, ?, ?, ?)";
    private String readSQL = "select * from room";
    private String updateSQL = "update room set type = ?, price = ?, available = ? where roomNumber = ?";
    private String deleteSQL = "delete from room";

    public RoomRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Room createRoom(@Valid Room room){
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

    public Room updateRoom(@Valid Room room){
        jdbcTemplate.update(updateSQL,
                room.getType(),
                room.getPrice(),
                room.getAvailable(),
                room.getRoomNumber()
        );
        return room;
    }

    public void deleteRoom(Integer roomNumber){
        jdbcTemplate.update(deleteSQL, roomNumber);
    }

}
