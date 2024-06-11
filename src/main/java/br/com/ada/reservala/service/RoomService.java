package br.com.ada.reservala.service;

import br.com.ada.reservala.domain.Room;
import br.com.ada.reservala.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    private Boolean available;


    private RoomRepository roomRepository;


    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room createRoom(Room room) {
        return roomRepository.createRoom(room);
    }

    public List<Room> readRoom() {
        return roomRepository.readRoom();
    }

    public Room updateRoom(Room room) {
        return roomRepository.updateRoom(room);
    }

    public void deleteRoom(Integer roomNumber) {
        roomRepository.deleteRoom(roomNumber);
    }

    //Deve calcular o percentual de quartos ocupados
    public Double getOcupation() {
        return 100d;
    }


    //Deve calcular a receita obtida
    public Double getRevenue() {
        return 100d;
    }

    public String findById(int roomNumber) {
        Optional<Room> room = roomRepository.readRoom().stream()
                .filter(r -> r.getRoomNumber() == roomNumber)
                .findFirst();

        if (room.isPresent()) {
            System.out.println("Quarto encontrado: " + room.get());
            return room.get().toString();
        } else {
            System.out.println("Quarto não encontrado para o número: " + roomNumber);
            return "Numero do quarto informado nao encontrado";
        }
    }

}



