package com.example.demo.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "title cannot be empty")
    @Column(nullable = false)
    private String title;
    private Boolean completed = false;
    public Todo(){

    }
    public Long getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public Boolean getCompleted(){
        return completed;
    }
    public void setCompleted(Boolean completed){
        this.completed = completed;

    }
}
