package tech.itpark.jdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class JdbcApplicationTest {
  @Autowired
  MockMvc mockMvc;

  @Test
  void crudTest() throws Exception {
    mockMvc.perform(get("/flats"))
        .andExpect(content().string("[{\"id\":1,\"ownerId\":1,\"district\":\"Ново-Савиновский\",\"price\":3,\"rooms\":1},{\"id\":2,\"ownerId\":2,\"district\":\"Кировский\",\"price\":5,\"rooms\":3},{\"id\":3,\"ownerId\":2,\"district\":\"Кировский\",\"price\":3,\"rooms\":3}]"));

    mockMvc.perform(get("/flats/1"))
        .andExpect(content().string("{\"id\":1,\"ownerId\":1,\"district\":\"Ново-Савиновский\",\"price\":3,\"rooms\":1}"));

    // flats/search?district=...&rooms=1
    mockMvc.perform(get("/flats/search")
        .queryParam("district", "Ново-Савиновский")
        .queryParam("rooms", String.valueOf(1)
        )
    ).andExpect(content().string("[{\"id\":1,\"ownerId\":1,\"district\":\"Ново-Савиновский\",\"price\":3,\"rooms\":1}]"));

    mockMvc.perform(post("/flats")
        .contentType("application/json")
        .content("{\"id\":0,\"ownerId\":1,\"district\":\"Ново-Савиновский\",\"price\":3,\"rooms\":1}")
    ).andExpect(content().string("{\"id\":4,\"ownerId\":1,\"district\":\"Ново-Савиновский\",\"price\":3,\"rooms\":1}"));
  }
}