package br.com.ada.reservala.service;

import br.com.ada.reservala.dto.RoomDtoRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class RoomServiceIntegrationTest {


    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;


    @Test
    void testCreateRoomSuccess() throws Exception {

        LinkedHashMap<String, Object> room = new LinkedHashMap<>();
        room.put("roomNumber", 71);
        room.put("type", "Deluxe");
        room.put("price", 450.99);
        room.put("available", true);


        String content = objectMapper.writeValueAsString(room);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/room")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

    }

    @Test
    void testCreateRoomDuplicated() throws Exception {

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
        ).andExpect(status().isOk());

        LinkedHashMap<String, Object> duplicateRoom = new LinkedHashMap<>(room);

        Exception exception = assertThrows(ServletException.class, () -> {
            mockMvc.perform(MockMvcRequestBuilders
                    .post("/room")
                    .content(objectMapper.writeValueAsString(duplicateRoom))
                    .contentType(MediaType.APPLICATION_JSON)
            );
        });

        assertTrue(exception.getMessage().contains("Unique index or primary key violation"));
    }


    @Test
    public void testCreateRoomWithNegativePrice() throws Exception {

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
        ).andExpect(status().isBadRequest());
    }


    @Test
    public void testCreateRoomWithNegativeRoomNumber() throws Exception {

        LinkedHashMap<String, Object> room = new LinkedHashMap<>();
        room.put("roomNumber", -7);
        room.put("type", "Deluxe");
        room.put("price", 450.99);
        room.put("available", true);


        String content = objectMapper.writeValueAsString(room);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/room")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }


    @Test
    public void testCreateRoomWithNegativeRoomNumberAndPrice() throws Exception {

        LinkedHashMap<String, Object> room = new LinkedHashMap<>();
        room.put("roomNumber", -7);
        room.put("type", "Deluxe");
        room.put("price", -450);
        room.put("available", true);


        String content = objectMapper.writeValueAsString(room);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/room")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }


    @Test
    void testCreateRoomWithDoublePriceValue() throws Exception {

        LinkedHashMap<String, Object> room = new LinkedHashMap<>();
        room.put("roomNumber", 7);
        room.put("type", "Deluxe");
        room.put("price", 1892.85);
        room.put("available", true);


        String content = objectMapper.writeValueAsString(room);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/room")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    void testCreateRoomWithAvailableFalse() throws Exception {
        LinkedHashMap<String, Object> room = new LinkedHashMap<>();
        room.put("roomNumber", 7);
        room.put("type", "Deluxe");
        room.put("price", 1892);
        room.put("available", false);


        String content = objectMapper.writeValueAsString(room);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/room")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }


    @Test
    void testCreateRoomWithAvailableNull() throws Exception {
        LinkedHashMap<String, Object> room = new LinkedHashMap<>();
        room.put("roomNumber", 7);
        room.put("type", "Deluxe");
        room.put("price", 1892);
        room.put("available", null);


        String content = objectMapper.writeValueAsString(room);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/room")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }


    @Test
    void testCreateRoomWithTypeNull() throws Exception {
        LinkedHashMap<String, Object> room = new LinkedHashMap<>();
        room.put("roomNumber", 7);
        room.put("type", null);
        room.put("price", 1892);
        room.put("available", false);


        String content = objectMapper.writeValueAsString(room);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/room")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }


    @Test
    void testCreateRoomWithTypeEqualsToNumber() throws Exception {
        LinkedHashMap<String, Object> room = new LinkedHashMap<>();
        room.put("roomNumber", 7);
        room.put("type", 1233);
        room.put("price", 1892);
        room.put("available", true);


        String content = objectMapper.writeValueAsString(room);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/room")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }



    @Test
    void readRoom() {

    }

    @Test
    void testUpdateRoomSuccess() throws Exception {
        RoomDtoRequest roomDto = new RoomDtoRequest(88, "Suite", 300, true);

        LinkedHashMap<String, Object> room = new LinkedHashMap<>();
        room.put("roomNumber", 88);
        room.put("type", "Deluxe");
        room.put("price", 450.99);
        room.put("available", true);


        String content = objectMapper.writeValueAsString(room);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/room")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders
                .put("/room")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(roomDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.roomNumber").value(88))
                .andExpect(jsonPath("$.type").value("Suite"))
                .andExpect(jsonPath("$.price").value(300))
                .andExpect(jsonPath("$.available").value(true));

    }


    @Test
    void testUpdateRoomWithNonExistentRoom() throws Exception{
        RoomDtoRequest roomDto = new RoomDtoRequest(2, "Suite", 300, true);

        /*LinkedHashMap<String, Object> room = new LinkedHashMap<>();
        room.put("roomNumber", 1);
        room.put("type", "Deluxe");
        room.put("price", 450.99);
        room.put("available", true);


        String content = objectMapper.writeValueAsString(room);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/room")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());*/

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/room")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(roomDto))
        ).andExpect(status().isNotFound());
    }


    @Test
    void testUpdateRoomWithNullBody() throws Exception{
        /*LinkedHashMap<String, Object> room = new LinkedHashMap<>();
        room.put("roomNumber", 1);
        room.put("type", "Deluxe");
        room.put("price", 450.99);
        room.put("available", true);


        String content = objectMapper.writeValueAsString(room);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/room")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());*/

        mockMvc.perform(MockMvcRequestBuilders
                .put("/room")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")
        ).andExpect(status().isBadRequest());
    }


    @Test
    void testUpdateRoomWithInvalidRoomNumber() throws Exception {
        /*LinkedHashMap<String, Object> room = new LinkedHashMap<>();
        room.put("roomNumber", 1);
        room.put("type", "Deluxe");
        room.put("price", 450.99);
        room.put("available",true);


        String content = objectMapper.writeValueAsString(room);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/room")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());*/

        RoomDtoRequest roomDto = new RoomDtoRequest(-1, "Premium", 300, false);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/room")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(roomDto))
        ).andExpect(status().isBadRequest());
    }


    @Test
    void testUpdateRoomWithInvalidPrice() throws Exception {
        /*LinkedHashMap<String, Object> room = new LinkedHashMap<>();
        room.put("roomNumber", 1);
        room.put("type", "Deluxe");
        room.put("price", 450.99);
        room.put("available", true);


        String content = objectMapper.writeValueAsString(room);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/room")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());*/

        RoomDtoRequest roomDto = new RoomDtoRequest(71, "Premium", -300, false);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/room")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(roomDto))
        ).andExpect(status().isBadRequest());
    }


    @Test
    void testUpdateRoomWithTypeNull() throws Exception {
        /*LinkedHashMap<String, Object> room = new LinkedHashMap<>();
        room.put("roomNumber", 1);
        room.put("type", "Deluxe");
        room.put("price", 450.99);
        room.put("available",true);


        String content = objectMapper.writeValueAsString(room);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/room")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());*/

        RoomDtoRequest roomDto = new RoomDtoRequest(71, null, 300, false);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/room")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(roomDto))
        ).andExpect(status().isBadRequest());
    }


    @Test
    void testUpdateRoomWithAvailableNull() throws Exception {
        /*LinkedHashMap<String, Object> room = new LinkedHashMap<>();
        room.put("roomNumber", 1);
        room.put("type", "Deluxe");
        room.put("price", 450.99);
        room.put("available", true);


        String content = objectMapper.writeValueAsString(room);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/room")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());*/

        RoomDtoRequest roomDto = new RoomDtoRequest(71, "Premium", 300, null);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/room")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(roomDto))
        ).andExpect(status().isBadRequest());
    }

    @Test
    void testDeleteRoomSuccess() throws Exception {
        RoomDtoRequest roomDto = new RoomDtoRequest(101, "Standard", 250, true);
        mockMvc.perform(MockMvcRequestBuilders.post("/room")
                        .content(objectMapper.writeValueAsString(roomDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.delete("/room/101"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteRoomNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/room/9999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteRoomInvalidNumber() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/room/0"))
                .andExpect(status().isBadRequest());
    }

}
