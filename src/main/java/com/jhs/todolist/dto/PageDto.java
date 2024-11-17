package com.jhs.todolist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageDto {
    private int pageIdx;
    private String displayValue;
}
