package br.com.ada.reservala.controller;

import br.com.ada.reservala.domain.Room;
import br.com.ada.reservala.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    public RoomController(RoomService roomService){
        this.roomService = roomService;
    }

    RoomService roomService;

    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody Room room){
        return ResponseEntity.ok(roomService.createRoom(room));
    }

    @GetMapping
    public ResponseEntity<List<Room>> readRoom(){
        return ResponseEntity.ok(roomService.readRoom());
    }

    @PutMapping
    public ResponseEntity<Room> updateRoom(@RequestBody Room room){

        return ResponseEntity.ok(roomService.updateRoom(room));
    }

    @DeleteMapping
    @RequestMapping("/{roomNumber}")
    public ResponseEntity<Void> deleteRoom(@PathVariable("roomNumber") Integer roomNumber){
        roomService.deleteRoom(roomNumber);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Double> getOcupation(){
        return ResponseEntity.ok(roomService.getOcupation());
    }

    public ResponseEntity<Double> getRevenue(){
        return ResponseEntity.ok(roomService.getOcupation());
    }

}
