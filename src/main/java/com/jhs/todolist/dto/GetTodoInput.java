package com.jhs.todolist.dto;

import com.jhs.todolist.type.Filter;
import lombok.Data;

@Data
public class GetTodoInput {

    private int pageIdx = 0;
    private Filter filter = Filter.ALL;

}
