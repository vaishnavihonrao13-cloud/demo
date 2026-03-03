import React, { useState, useEffect } from "react";
import './index.css';

function App() {
    const [todos, setTodos] = useState([]);
    const [title, setTitle] = useState("");

    useEffect(() => {
        fetch("http://localhost:8080/todos")
            .then(res => res.json())
            .then(data => setTodos(data))
            .catch(err => console.error("Error fetching todos:", err));
    }, []);

    const addTodo = () => {
        if (title.trim() === "") return;

        fetch("http://localhost:8080/todos", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ title, completed: false })
        })
            .then(res => res.json())
            .then(newTodo => {
                setTodos([...todos, newTodo]);
                setTitle("");
            })
            .catch(err => console.error("Error adding todo:", err));
    };

    const deleteTodo = (id) => {
        fetch(`http://localhost:8080/todos/${id}`, { method: "DELETE" })
            .then(() => setTodos(todos.filter(todo => todo.id !== id)))
            .catch(err => console.error("Error deleting todo:", err));
    };

    const toggleTodo = (todo) => {
        fetch(`http://localhost:8080/todos/${todo.id}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ ...todo, completed: !todo.completed })
        })
            .then(res => res.json())
            .then(updatedTodo => {
                setTodos(todos.map(t => t.id === updatedTodo.id ? updatedTodo : t));
            })
            .catch(err => console.error("Error updating todo:", err));
    };

    return (
        <div className="container">
            <h1>Todo App</h1>
            <div>
                <input
                    type="text"
                    value={title}
                    onChange={e => setTitle(e.target.value)}
                    placeholder="Enter todo"
                />
                <button onClick={addTodo} disabled={title.trim() === ""}>Add</button>
            </div>
            <ul>
                {todos.map(todo => (
                    <li key={todo.id}>
            <span
                className={todo.completed ? "completed" : ""}
                onClick={() => toggleTodo(todo)}
            >
              {todo.title}
            </span>
                        <button onClick={() => deleteTodo(todo.id)}>Delete</button>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default App;