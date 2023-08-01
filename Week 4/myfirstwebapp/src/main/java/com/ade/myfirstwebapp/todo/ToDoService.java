package com.ade.myfirstwebapp.todo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ToDoService {

    private static List<ToDo> todos = new ArrayList<>( );

    static {
        todos.add(new ToDo(1, "jinwoo", "Learn AWS", LocalDate.now().plusYears(1), false));
        todos.add(new ToDo(2, "jinwoo", "Learn DevOps", LocalDate.now().plusYears(2), false));
        todos.add(new ToDo(3, "jinwoo", "Learn Full Stack Dev", LocalDate.now().plusYears(3), false));
    }

    public List<ToDo> findByUserName(String username) {
        return todos;
    }

}
