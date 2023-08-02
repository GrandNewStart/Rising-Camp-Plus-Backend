package com.ade.myfirstwebapp.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class ToDoService {

    private static List<ToDo> todos = new ArrayList<>( );
    private static int todosCount = 0;

    static {
        todos.add(new ToDo(++todosCount, "jinwoo", "Learn AWS", LocalDate.now().plusYears(1), false));
        todos.add(new ToDo(++todosCount, "jinwoo", "Learn DevOps", LocalDate.now().plusYears(2), false));
        todos.add(new ToDo(++todosCount, "jinwoo", "Learn Full Stack Dev", LocalDate.now().plusYears(3), false));
    }

    public List<ToDo> findByUserName(String username) {
        return todos;
    }

    public void addTodo(String username, String description, LocalDate targetDate, boolean isDone) {
        todos.add(new ToDo(++todosCount, username, description, targetDate, isDone));
    }

    public void deleteById(int id) {
        Predicate<? super ToDo> predicate = todo -> todo.getId() == id;
        todos.removeIf(predicate);
    }

    public ToDo findById(int id) {
        Predicate<? super ToDo> predicate = todo -> todo.getId() == id;
        return todos.stream().filter(predicate).findFirst().get();
    }

    public List<ToDo> findByUsername(String username) {
        Predicate<? super ToDo> predicate = todo -> todo.getUsername().equals(username);
        return todos.stream().filter(predicate).toList();
    }

    public void updateTodo(@Valid ToDo todo) {
        this.deleteById(todo.getId());
        todos.add(todo);
    }

}
