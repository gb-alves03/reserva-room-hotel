package br.com.ada.reservala.service;

import br.com.ada.reservala.domain.Room;
import br.com.ada.reservala.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }
    public Room createRoom(Room room) {
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
        if (room.getAvailable() == null  || room.getAvailable() ) {
            throw new IllegalArgumentException("O availability não pode ser vazio.");
        }

        return roomRepository.createRoom(room);
    }

    public List<Room> readRoom(){
        return roomRepository.readRoom();
    }

    public Room updateRoom(Room room){
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
