package com.example.backend.service;

import com.example.backend.models.Todo;
import com.example.backend.repo.TodoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TodoService {

    private TodoRepo todoRepo;

    public TodoService(TodoRepo todoRepo) {
        this.todoRepo = todoRepo;
    }

    public Todo addTodo(Todo todo) {
        return todoRepo.addTodo(todo);
    }

    public List<Todo> getTodoList() {
        return todoRepo.getTodoList();
    }
    public Todo getTodoById(String id) {
        return todoRepo.getTodoById(id);
    }

    // Methode kopiert
    public Todo updateTodoById(String id, Todo todo) {
        Todo todoToUpdate = todoRepo.getTodoById(id);
        if (todoToUpdate == null) {
            throw new NoSuchElementException("No Element with this ID");
        }
        todoToUpdate.setDescription(todo.getDescription());
        todoToUpdate.setStatus(todo.getStatus());

        return todoToUpdate;
    }

    // Methode kopiert
    public void deleteById(String id) {
        Todo todo = todoRepo.getTodoById(id);
        if (todo == null) {
            throw new NoSuchElementException("No Element with this ID");
        }
        todoRepo.delete(todo);
    }
}
