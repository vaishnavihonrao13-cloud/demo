import React, { useState, useEffect } from "react";

function App() {
    const [todos, setTodos] = useState([]);
    const [title, setTitle] = useState("");

    // 🔹 Load todos on page load
    useEffect(() => {
        fetch("http://localhost:8080/todos")
            .then(res => res.json())
            .then(data => setTodos(data));
    }, []);

    // 🔹 Add todo
    const addTodo = () => {
        if (title.trim() === "") return;

        fetch("http://localhost:8080/todos", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                title: title,
                completed: false
            })
        })
            .then(res => res.json())
            .then(newTodo => {
                setTodos([...todos, newTodo]);
                setTitle("");
            });
    };

    // 🔹 Delete todo
    const deleteTodo = (id) => {
        fetch(`http://localhost:8080/todos/${id}`, {
            method: "DELETE"
        }).then(() => {
            setTodos(todos.filter(todo => todo.id !== id));
        });
    };

    // 🔹 Toggle completed
    const toggleTodo = (todo) => {
        fetch(`http://localhost:8080/todos/${todo.id}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                ...todo,
                completed: !todo.completed
            })
        })
            .then(res => res.json())
            .then(updatedTodo => {
                setTodos(
                    todos.map(t =>
                        t.id === updatedTodo.id ? updatedTodo : t
                    )
                );
            });
    };

    return (
        <div style={{ textAlign: "center", marginTop: "50px" }}>
            <h1>Todo App</h1>

            <input
                type="text"
                value={title}
                onChange={(e) => setTitle(e.target.value)}
                placeholder="Enter todo"
            />

            <button onClick={addTodo}>Add</button>

            <ul>
                {todos.map(todo => (
                    <li key={todo.id}>
            <span
                onClick={() => toggleTodo(todo)}
                style={{
                    textDecoration: todo.completed ? "line-through" : "none",
                    cursor: "pointer"
                }}
            >
              {todo.title}
            </span>

                        <button
                            onClick={() => deleteTodo(todo.id)}
                            style={{ marginLeft: "10px" }}
                        >
                            Delete
                        </button>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default App;