package tech.itpark.jdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class JdbcApplicationTest {
  @Autowired
  MockMvc mockMvc;

  @Test
  void crudTest() throws Exception {
    mockMvc.perform(get("/flats"))
        .andExpect(
            content()
                .json("[\n" +
                    "  {\n" +
                    "    \"id\": 1,\n" +
                    "    \"ownerId\": 1,\n" +
                    "    \"district\": \"Ново-Савиновский\",\n" +
                    "    \"price\": 3,\n" +
                    "    \"rooms\": 1\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"id\": 2,\n" +
                    "    \"ownerId\": 2,\n" +
                    "    \"district\": \"Кировский\",\n" +
                    "    \"price\": 5,\n" +
                    "    \"rooms\": 3\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"id\": 3,\n" +
                    "    \"ownerId\": 2,\n" +
                    "    \"district\": \"Кировский\",\n" +
                    "    \"price\": 3,\n" +
                    "    \"rooms\": 3\n" +
                    "  }\n" +
                    "]")
        );

    mockMvc.perform(get("/flats/1"))
        .andExpect(
            content()
                .json("{\n" +
                    "  \"id\": 1,\n" +
                    "  \"ownerId\": 1,\n" +
                    "  \"district\": \"Ново-Савиновский\",\n" +
                    "  \"price\": 3,\n" +
                    "  \"rooms\": 1\n" +
                    "}")
        );

    mockMvc.perform(
        get("/flats/search")
            .queryParam("district", "Ново-Савиновский")
            .queryParam("rooms", String.valueOf(1))
    )
        .andExpect(
            content()
                .json("[\n" +
                    "  {\n" +
                    "    \"id\": 1,\n" +
                    "    \"ownerId\": 1,\n" +
                    "    \"district\": \"Ново-Савиновский\",\n" +
                    "    \"price\": 3,\n" +
                    "    \"rooms\": 1\n" +
                    "  }\n" +
                    "]")
        );

    mockMvc.perform(
        post("/flats")
        .contentType("application/json")
        .content("{\n" +
            "  \"id\": 0,\n" +
            "  \"ownerId\": 1,\n" +
            "  \"district\": \"Ново-Савиновский\",\n" +
            "  \"price\": 3,\n" +
            "  \"rooms\": 1\n" +
            "}")
    )
        .andExpect(
            content()
                .json("{\n" +
                    "  \"id\": 4,\n" +
                    "  \"ownerId\": 1,\n" +
                    "  \"district\": \"Ново-Савиновский\",\n" +
                    "  \"price\": 3,\n" +
                    "  \"rooms\": 2\n" +
                    "}")
        );
  }
}