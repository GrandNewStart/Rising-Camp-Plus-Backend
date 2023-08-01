package com.ade.myfirstwebapp.todo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import java.util.List;

@Controller
@SessionAttributes("name")
public class ToDoController {

    private ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        super();
        this.toDoService = toDoService;
    }

    @RequestMapping("/list-todos")
    public String listAllTodos(ModelMap map) {
        List<ToDo> todos = toDoService.findByUserName("jinwoo");
        map.addAttribute("todos", todos);
        return "listTodos";
    }

}
