package com.example.backend.repo;

import com.example.backend.models.Status;
import com.example.backend.models.Todo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class TodoRepo {
    private List<Todo> toodList = new ArrayList<>();

    // warum
    public TodoRepo() {
        Todo todo1 = new Todo("To do 1", (Status.valueOf("OPEN")), "100");
        toodList.add(todo1);
    }

    public Todo addTodo(Todo todo) {
        toodList.add(todo);
        return todo;
    }

    public List<Todo> getTodoList() {
        return toodList;
    }

    // Methode kopiert
    public Todo getTodoById(String id) {
        for (Todo todo : toodList) {
            if (todo.getId().equals(id)) {
                return todo;
            }
        }
        throw new NoSuchElementException("No Element with this ID");
    }

    // Methode kopiert
    public void delete(Todo TodoToDelete) {
        toodList.remove(TodoToDelete);
    }
}



