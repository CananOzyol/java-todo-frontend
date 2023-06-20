package com.example.backend.service;

import com.example.backend.models.Status;
import com.example.backend.models.Todo;
import com.example.backend.repo.TodoRepo;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class TodoServiceTest {

    private final TodoRepo todoRepo = mock(TodoRepo.class); // warum private final
    private final  TodoService todoService = new TodoService(todoRepo);

    @Test // wie viel Sinn ergibt dieser Test, fragwürdig
    void addTodo_thenReturnTheTodo() {
        // GIVEN
        Todo todo1 = new Todo("To do 1", (Status.valueOf("OPEN")), "101");
        Todo expected = todo1;

        // WHEN
        when(todoRepo.addTodo(todo1)).thenReturn(expected);
        Todo actual = todoService.addTodo(todo1);

        // THEN
        assertEquals(expected, actual);
        verify(todoRepo).addTodo(todo1);
    }

    @Test
    void getTodoList_returnListOfAllTodos() {
        // GIVEN
        TodoRepo todoRepo = mock(TodoRepo.class);
        TodoService todoService = new TodoService(todoRepo);
        List<Todo> expectedTodoList = List.of(new Todo("To do 1", (Status.valueOf("DONE")), "101"));

        // WHEN
        when(todoRepo.getTodoList()).thenReturn(expectedTodoList);
        List<Todo> actualTodoList = todoService.getTodoList();

        // THEN
        assertEquals(expectedTodoList, actualTodoList);
        verify(todoRepo).getTodoList();
    }

    @Test
    void getTodoById() {
        // GIVEN
        Todo expected = new Todo("description", (Status.valueOf("DONE")), "103");

        // WHEN
        when(todoRepo.getTodoById("103")).thenReturn(expected);
        Todo actual = todoService.getTodoById("103");

        // THEN
        assertEquals(expected, actual);
        verify(todoRepo).getTodoById("103");  // nicht mit einer anderen id möglich
    }

    @Test
    void updateTodoById() {
        // GIVEN
        Todo todo1 = new Todo("To do 1", (Status.valueOf("OPEN")), "100");
        Todo expected = new Todo("To do 2", (Status.valueOf("DONE")), "100");

        // WHEN
        when(todoRepo.getTodoById("100")).thenReturn(todo1);
        Todo actual = todoService.updateTodoById("100", expected);

        // THEN
        assertEquals(expected, actual);
        // warum müssen alle die gleiche Id haben?
    }

    @Test
    void deleteById() {
        // WHEN
        when(todoRepo.getTodoById("1")).thenReturn(null);
        // THEN
        assertThrows(NoSuchElementException.class, ()
                -> todoService.deleteById("101"), "No Element with this id");
    }
}



