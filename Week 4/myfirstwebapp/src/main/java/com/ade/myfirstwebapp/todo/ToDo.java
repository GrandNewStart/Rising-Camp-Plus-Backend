package com.ade.myfirstwebapp.todo;

import java.time.LocalDate;

public class ToDo {
    private int id;
    private String username;
    private String description;
    private LocalDate targetDate;
    private boolean isDone;

    public ToDo(int id, String username, String description, LocalDate targetDate, boolean isDone) {
        this.id = id;
        this.username = username;
        this.description = description;
        this.targetDate = targetDate;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "ToDo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", description='" + description + '\'' +
                ", targetDate=" + targetDate +
                ", isDone=" + isDone +
                '}';
    }
}
