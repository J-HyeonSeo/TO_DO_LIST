package com.jhs.todolist.service;

import com.jhs.todolist.dto.CreateTodoInput;
import com.jhs.todolist.dto.GetTodoInput;
import com.jhs.todolist.dto.TodoDto;
import com.jhs.todolist.dto.UpdateTodoCheckedInput;
import org.springframework.data.domain.Page;

public interface TodoService {

    void createTodo(CreateTodoInput input);
    Page<TodoDto> getTodoList(GetTodoInput input);
    void updateTodoChecked(Long todoId, UpdateTodoCheckedInput input);
    void deleteTodo(Long todoId);

}
