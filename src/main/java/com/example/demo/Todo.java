package com.example.demo;
import jakarta.persistence.*;

@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Boolean completed;
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
