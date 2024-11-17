package com.jhs.todolist.service.impl;

import com.jhs.todolist.dto.CreateTodoInput;
import com.jhs.todolist.dto.GetTodoInput;
import com.jhs.todolist.dto.TodoDto;
import com.jhs.todolist.dto.UpdateTodoCheckedInput;
import com.jhs.todolist.entity.TodoEntity;
import com.jhs.todolist.repository.TodoRepository;
import com.jhs.todolist.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Override
    public void createTodo(CreateTodoInput input) {
        TodoEntity todo = TodoEntity.builder().content(input.getContent()).build();
        todoRepository.save(todo);
    }

    @Override
    public Page<TodoDto> getTodoList(GetTodoInput input) {

        return switch (input.getFilter()) {
            case ALL -> todoRepository.findAll(PageRequest.of(input.getPageIdx(), 7))
                .map(TodoEntity::toDto);
            case CHECKED -> todoRepository.findAllByCheckedIsTrue(PageRequest.of(input.getPageIdx(), 7))
                .map(TodoEntity::toDto);
            case NO_CHECKED -> todoRepository.findAllByCheckedIsFalse(PageRequest.of(input.getPageIdx(), 7))
                .map(TodoEntity::toDto);
        };

    }

    @Override
    public void updateTodoChecked(Long todoId, UpdateTodoCheckedInput input) {
        TodoEntity todo = todoRepository.findById(todoId)
            .orElseThrow(() -> new RuntimeException("Todo is not found"));

        todo.setChecked(input.isChecked());
    }

    @Override
    public void deleteTodo(Long todoId) {
        todoRepository.deleteById(todoId);
    }

}
