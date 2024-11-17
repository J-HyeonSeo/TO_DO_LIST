package com.jhs.todolist.controller.rest;

import com.jhs.todolist.dto.CreateTodoInput;
import com.jhs.todolist.dto.UpdateTodoCheckedInput;
import com.jhs.todolist.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Ajax처리로 Restful하게 처리되는 방식으로 호출하는 컨트롤러,
 * RestController를 지정하여 기본적으로 View의 형태를 JsonView의 형식으로 지정함.
 */
@RestController
@RequestMapping("/rest/v1")
@RequiredArgsConstructor
public class TodoListRestController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody CreateTodoInput input) {
        todoService.createTodo(input);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/checked/{todoId}")
    public ResponseEntity<?> updateTodoChecked(@RequestBody UpdateTodoCheckedInput input, @PathVariable long todoId) {
        todoService.updateTodoChecked(todoId, input);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<?> deleteTodo(@PathVariable long todoId) {
        todoService.deleteTodo(todoId);
        return ResponseEntity.ok().build();
    }

}
