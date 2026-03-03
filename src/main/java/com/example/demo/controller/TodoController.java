package com.example.demo.controller;
import com.example.demo.exception.TodoNotFoundException;
import com.example.demo.entity.Todo;
import com.example.demo.repository.TodoRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")   // 🔥 For React connection
@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos() {
        return ResponseEntity.ok(todoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException("Todo not found"));

        return ResponseEntity.ok(todo);
    }


    @PostMapping
    public ResponseEntity<Todo> createTodo(@Valid @RequestBody Todo todo) {

        Todo savedTodo = todoRepository.save(todo);

        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(
            @PathVariable Long id,
            @Valid @RequestBody Todo updateTodo) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException("Todo not found"));

        todo.setTitle(updateTodo.getTitle());
        todo.setCompleted(updateTodo.getCompleted());

        return ResponseEntity.ok(todoRepository.save(todo));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {

        if (!todoRepository.existsById(id)) {
            throw new TodoNotFoundException("Todo not found");
        }

        todoRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}