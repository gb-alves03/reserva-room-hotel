package br.com.ada.reservala.service;

import br.com.ada.reservala.domain.Room;
import br.com.ada.reservala.dto.RoomDtoRequest;
import br.com.ada.reservala.repository.RoomRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }

    public Room createRoom(@Valid Room room){
        // Validações das regras de negócio do create
        if (room.getRoomNumber() < 0 || room.getRoomNumber() == null) {
            throw new IllegalArgumentException("O Number não pode ser negativo ou vazio.");
        }
        if (room.getType() == null || !room.getType().matches("\\D+")) {
            throw new IllegalArgumentException("O type não pode ser vazio ou numerico");
        }
        if (room.getPrice() < 0) {
            throw new IllegalArgumentException("O price não pode ser negativo.");
        }
        if (room.getAvailable() == null) {
            throw new IllegalArgumentException("O availability não pode ser vazio.");
        }
        return roomRepository.createRoom(room);
    }

    public List<Room> readRoom(){
        return roomRepository.readRoom();
    }

    public Room updateRoom(@Valid Room room){
        if (room.getRoomNumber() < 0 || room.getRoomNumber() == null) {
            throw new IllegalArgumentException("O roomNumber não pode ser negativo ou vazio");
        }
        if (room.getType() == null || !room.getType().matches("\\D+")) {
            throw new IllegalArgumentException("O type não pode ser vazio ou numerico");
        }
        if (room.getPrice() < 0) {
            throw new IllegalArgumentException("O price não pode ser negativo");
        }
        if (room.getAvailable() == null) {
            throw new IllegalArgumentException("O availability não pode ser vazio");
        }
        return roomRepository.updateRoom(room);
    }

    public void deleteRoom(Integer roomNumber){
        roomRepository.deleteRoom(roomNumber);
    }

    //Deve calcular o percentual de quartos ocupados
    public Double getOcupation(){
        return 100d;
    }

    //Deve calcular a receita obtida
    public Double getRevenue(){
        return 100d;
    }

}
