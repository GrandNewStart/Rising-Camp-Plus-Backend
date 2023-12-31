package com.ade.myfirstwebapp.todo;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

//@Controller
@SessionAttributes("name")
public class ToDoController {

    private ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        super();
        this.toDoService = toDoService;
    }

    @RequestMapping("/list-todos")
    public String listAllTodos(ModelMap map) {
        String username = getLoggedInUsername(map);
        List<ToDo> todos = toDoService.findByUserName(username);
        map.addAttribute("todos", todos);
        return "listTodos";
    }
    @RequestMapping(value = "/add-todo", method = RequestMethod.GET)
    public String showTodoPage(ModelMap map) {
        ToDo todo = new ToDo(
                0,
                getLoggedInUsername(map),
                "Default Desc",
                LocalDate.now().plusYears(1),
                false
        );
        map.addAttribute("todo", todo);
        return "todo";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
    public String addNewTodo(ModelMap map, @Valid ToDo todo, BindingResult result) {
        if (result.hasErrors()) {
            map.addAttribute("todo", todo);
            return "todo";
        }
        this.toDoService.addTodo(
                getLoggedInUsername(map),
                todo.getDescription(),
                todo.getTargetDate(),
                false
        );
        return "redirect:list-todos";
    }

    @RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
    public String deleteTodo(@RequestParam int id) {
        this.toDoService.deleteById(id);
        return "redirect:list-todos";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.GET)
    public String showUpdateTodo(@RequestParam int id, ModelMap map) {
        ToDo todo = this.toDoService.findById(id);
        map.addAttribute("todo", todo);
        return "todo";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.POST)
    public String updateTodo(@Valid ToDo todo, BindingResult result, ModelMap map) {
        if (result.hasErrors()) {
            map.addAttribute("todo", todo);
            return "todo";
        }
        todo.setUsername(getLoggedInUsername(map));
        this.toDoService.updateTodo(todo);
        return "redirect:list-todos";
    }

    private static String getLoggedInUsername(ModelMap map) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }


}
