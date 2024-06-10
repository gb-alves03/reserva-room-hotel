package br.com.ada.reservala.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.util.NestedServletException;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@AutoConfigureMockMvc
class RoomServiceIntegrationTest {


    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;


    @Test
    void deveriaCriarRoomNaoNull() throws Exception {

        LinkedHashMap<String, Object> room = new LinkedHashMap<>();
        room.put("roomNumber", 71);
        room.put("type", "Deluxe");
        room.put("price", 450.99);
        room.put("available",false);


        String content = objectMapper.writeValueAsString(room);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/room")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void deveriaLancarExcecaooAoCriarQuartoComNumeroDeQuartoDuplicado() throws Exception {

        LinkedHashMap<String, Object> room = new LinkedHashMap<>();
        room.put("roomNumber", 654);
        room.put("type", "Deluxe");
        room.put("price", 1893);
        room.put("available", true);

        String content = objectMapper.writeValueAsString(room);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/room")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());

        LinkedHashMap<String, Object> duplicateRoom = new LinkedHashMap<>(room);

        Exception exception = assertThrows(NestedServletException.class, () -> {
            mockMvc.perform(MockMvcRequestBuilders
                    .post("/room")
                    .content(objectMapper.writeValueAsString(duplicateRoom))
                    .contentType(MediaType.APPLICATION_JSON)
            );
        });

        assertTrue(exception.getMessage().contains("Unique index or primary key violation"));
    }





    @Test
    public void priceNaoDeveriaSerNegativo() throws Exception{

        LinkedHashMap<String, Object> room = new LinkedHashMap<>();
        room.put("roomNumber", 7);
        room.put("type", "Deluxe");
        room.put("price", -450.99);
        room.put("available",false);


        String content = objectMapper.writeValueAsString(room);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/room")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());


    }

    @Test
    public void roomNumberNaoDeveriaSerNegativo() throws Exception{

        LinkedHashMap<String, Object> room = new LinkedHashMap<>();
        room.put("roomNumber", -7);
        room.put("type", "Deluxe");
        room.put("price", 450.99);
        room.put("available",false);


        String content = objectMapper.writeValueAsString(room);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/room")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());


    }

    @Test
    public void roomNumberEPriceNaoDeveriaSerNegativo() throws Exception{

        LinkedHashMap<String, Object> room = new LinkedHashMap<>();
        room.put("roomNumber", -7);
        room.put("type", "Deluxe");
        room.put("price", -450.99);
        room.put("available",false);


        String content = objectMapper.writeValueAsString(room);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/room")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());


    }


    @Test
    void deveriaCriarRoomComPriceValoresDouble() throws Exception {

        LinkedHashMap<String, Object> room = new LinkedHashMap<>();
        room.put("roomNumber", 7);
        room.put("type", "Deluxe");
        room.put("price", 1892.85);
        room.put("available",false);


        String content = objectMapper.writeValueAsString(room);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/room")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void deveriaFalhaAoCriarRoomComAvailableIgualNull() throws Exception {
        LinkedHashMap<String, Object> room = new LinkedHashMap<>();
        room.put("roomNumber", 7);
        room.put("type", "Deluxe");
        room.put("price", 1892.85);
        room.put("available", null);


        String content = objectMapper.writeValueAsString(room);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/room")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void deveriaFalhaAoCriarRoomComTypeIgualNull() throws Exception{
        LinkedHashMap<String, Object> room = new LinkedHashMap<>();
        room.put("roomNumber", 7);
        room.put("type", null);
        room.put("price", 1892.85);
        room.put("available", false);


        String content = objectMapper.writeValueAsString(room);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/room")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void deveriaFalhaAoCriarRoomComTypeIgualNumero() throws Exception{
        LinkedHashMap<String, Object> room = new LinkedHashMap<>();
        room.put("roomNumber", 7);
        room.put("type", 1233);
        room.put("price", 1892.85);
        room.put("available", null);


        String content = objectMapper.writeValueAsString(room);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/room")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @Test
    void readRoom() {

    }

    @Test
    void updateRoom() {

    }

    @Test
    void deleteRoom() {

    }

}
