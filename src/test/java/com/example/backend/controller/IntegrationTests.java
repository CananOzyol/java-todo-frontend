package com.example.backend.controller;


import com.example.backend.models.Status;
import com.example.backend.models.Todo;
import com.example.backend.repo.TodoRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class IntegrationTests {

    @Autowired
    private MockMvc Mvc;
    @Autowired
    private TodoRepo todoRepo;

    @Test
    @DirtiesContext
    void allTodos() throws Exception {
        // GIVEN
        todoRepo.addTodo(new Todo("To do 1", (Status.valueOf("OPEN")), "101"));
        String expected = """
                    [
                    {
                        "description": "To do 1",
                        "status": "OPEN",
                        "id": "101"
                    }  
                    ]        
                    """;
        // WHEN
        Mvc.perform(MockMvcRequestBuilders.get("/api/todo"))
                // THEN
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().json(expected));
    }

}