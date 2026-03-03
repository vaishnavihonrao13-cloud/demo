package com.example.demo.dto;

public class TodoDTO {

    private Long id;
    private String title;
    private Boolean completed;

    public TodoDTO(Long id, String title, Boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getCompleted() {
        return completed;
    }
}