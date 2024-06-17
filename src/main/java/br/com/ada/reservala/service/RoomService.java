package br.com.ada.reservala.service;

import br.com.ada.reservala.domain.Room;
import br.com.ada.reservala.exception.RoomNotFoundException;
import br.com.ada.reservala.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@Validated
public class RoomService {

    private final RoomRepository roomRepository;


    @Autowired
    public RoomService(RoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }

    public Room createRoom(Room room) {
        if (!room.getAvailable()) {
            throw new IllegalArgumentException("O available deve ser true");
        }
        validate(room);
        return roomRepository.createRoom(room);
    }

    public List<Room> readRoom(){
        return roomRepository.readRoom();
    }

    public Optional<Room> readRoomByRoomNumber(Integer roomNumber) {
        try {
            Room room = roomRepository.readRoomByRoomNumber(roomNumber);
            return Optional.of(room);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Room updateRoom(Room room) {
        validate(room);
        return roomRepository.updateRoom(room);
    }


    public boolean deleteRoom(Integer roomNumber) {
        if (roomNumber == null || roomNumber <= 0) {
            throw new IllegalArgumentException("O número do quarto deve ser positivo.");
        }

        try {
            return roomRepository.deleteRoom(roomNumber);
        } catch (RoomNotFoundException e) {
            throw e;
        } catch (DataAccessException e) {
            throw new RuntimeException("Erro ao excluir quarto: " + e.getMessage(), e);
        }
    }

    public int deleteAllRooms() {
        try {
            return roomRepository.deleteAllRooms();
        } catch (DataAccessException e) {
            throw new RuntimeException("Erro ao excluir todos os quartos: " + e.getMessage(), e);
        }
    }

    public Double getOcupation() {
        List<Room> rooms = roomRepository.readRoom();
        long totalRooms = rooms.size();
        long occupiedRooms = rooms.stream()
                .filter(room -> !room.getAvailable())
                .count();

        if (totalRooms == 0) {
            return 0d;
        }
        return (occupiedRooms / (double) totalRooms) * 100;
    }

    public Double getRevenue() {
        List<Room> rooms = roomRepository.readRoom();
        return rooms.stream()
                .filter(room -> !room.getAvailable())
                .mapToDouble(Room::getPrice)
                .sum();
    }

    private void validate(Room room) {
        if (room == null) {
            throw new IllegalArgumentException("Room não pode ser nulo");
        }
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
