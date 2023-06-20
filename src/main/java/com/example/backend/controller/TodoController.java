package com.example.backend.controller;

import com.example.backend.models.Todo;
import com.example.backend.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor

public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        return todoService.addTodo(todo);
    }
    @GetMapping
    public List<Todo> getTodoList() {
        return todoService.getTodoList();
    }

    @GetMapping("/{id}")
    public Todo getTodoById(@PathVariable String id) {
        return todoService.getTodoById(id);
    }

    @PutMapping("/{id}")
    public Todo updateTodoById(@PathVariable String id, @RequestBody Todo todo) {
        return todoService.updateTodoById(id, todo);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        todoService.deleteById(id);
    }

}
