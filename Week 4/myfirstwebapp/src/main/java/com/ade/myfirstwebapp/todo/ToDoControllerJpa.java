package com.ade.myfirstwebapp.todo;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes("name")
public class ToDoControllerJpa {
    private ToDoRepository repository;

    public ToDoControllerJpa(ToDoRepository repository) {
        super();
        this.repository = repository;
    }

    @RequestMapping("/list-todos")
    public String listAllTodos(ModelMap map) {
        String username = getLoggedInUsername(map);
        List<ToDo> todos = repository.findByUsername(username);
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
        String username = getLoggedInUsername(map);
        todo.setUsername(username);
        this.repository.save(todo);
        return "redirect:list-todos";
    }

    @RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
    public String deleteTodo(@RequestParam int id) {
        this.repository.deleteById(id);
        return "redirect:list-todos";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.GET)
    public String showUpdateTodo(@RequestParam int id, ModelMap map) {
        ToDo todo = this.repository.findById(id).get();
        map.addAttribute("todo", todo);
        return "todo";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.POST)
    public String updateTodo(@Valid ToDo todo, BindingResult result, ModelMap map) {
        if (result.hasErrors()) {
            map.addAttribute("todo", todo);
            return "todo";
        }
        String username = getLoggedInUsername(map);
        todo.setUsername(username);
        this.repository.save(todo);
        return "redirect:list-todos";
    }

    private static String getLoggedInUsername(ModelMap map) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }


}
