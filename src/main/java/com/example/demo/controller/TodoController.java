package com.example.demo.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.demo.entity.Todo;
import com.example.demo.repository.TodoRepository;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

        @GetMapping
        public List<Todo> getAllTodos() {
            return todoRepository.findAll();

    }

    @GetMapping("/{id}")
    public Todo getTodoById(@PathVariable Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));

    }

    @PostMapping
    public Todo createTodo(@Valid @RequestBody Todo todo) {
        return todoRepository.save(todo);
    }

    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable Long id, @Valid @RequestBody Todo updateTodo) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));
        todo.setTitle(updateTodo.getTitle());
        todo.setCompleted(updateTodo.getCompleted());
        return todoRepository.save(todo);

    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id) {
        if (!todoRepository.existsById(id)){
            throw new RuntimeException("Todo not found");

        }
        todoRepository.deleteById(id);

    }
}