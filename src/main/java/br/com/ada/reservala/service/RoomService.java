package br.com.ada.reservala.service;

import br.com.ada.reservala.domain.Room;
import br.com.ada.reservala.repository.RoomRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class RoomService {

    private RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }

    public Room createRoom(@Valid Room room){
        return roomRepository.createRoom(room);
    }

    public List<Room> readRoom(){
        return roomRepository.readRoom();
    }

    public Room updateRoom(@Valid Room room){
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
