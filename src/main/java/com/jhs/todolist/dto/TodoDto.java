package com.jhs.todolist.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TodoDto {
    private Long id;
    private String content;
    private boolean checked;
}
