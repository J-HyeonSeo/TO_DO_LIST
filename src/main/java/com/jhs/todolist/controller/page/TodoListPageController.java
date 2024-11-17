package com.jhs.todolist.controller.page;

import com.jhs.todolist.dto.GetTodoInput;
import com.jhs.todolist.dto.PageDto;
import com.jhs.todolist.dto.TodoDto;
import com.jhs.todolist.service.TodoService;
import com.jhs.todolist.type.Filter;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * ThymeLeaf 템플릿 엔진를 사용하여, html, css, js 와 같은 정적 리소스를 만들어
 * 응답해주는 컨트롤러.
 */
@Controller
@RequiredArgsConstructor
public class TodoListPageController {

    private final static int PAGE_NAV_BAR_SIZE = 5;
    private final TodoService todoService;

    @GetMapping
    public String getIndexPage() {
        return "redirect:/page/list.do";
    }

    @GetMapping("/page/list.do")
    public String getTodoListPage(Model model, @ModelAttribute GetTodoInput input) {

        if (input.getFilter() == null) input.setFilter(Filter.ALL);

        // todo list 목록 가져오기.
        Page<TodoDto> todoList = todoService.getTodoList(input);

        //5개씩 끊어서, 페이지 순차 배치 1-5, 5-10, 11-11
        int totalPages = todoList.getTotalPages();

        int startPageIdx = ((input.getPageIdx()) / PAGE_NAV_BAR_SIZE) * PAGE_NAV_BAR_SIZE + 1;
        int endPageIdx = Math.min(totalPages, startPageIdx + PAGE_NAV_BAR_SIZE - 1);

        List<PageDto> pageNavList = new ArrayList<>();

        // 첫 번째 페이지로 이동하는 버튼추가
        pageNavList.add(new PageDto(0, "<<"));
        // 이전 페이지로 이동하는 버튼 추가
        pageNavList.add(new PageDto(Math.max(0, startPageIdx - 2), "<"));

        // 페이지 버튼 추가
        for (int i = startPageIdx; i <= endPageIdx; i++) {
            pageNavList.add(new PageDto(i - 1, i + ""));
        }

        // 다음 페이지로 이동하는 버튼 추가
        pageNavList.add(new PageDto(Math.min(totalPages - 1, endPageIdx), ">"));
        // 마지막 페이지로 이동하는 버튼 추가
        pageNavList.add(new PageDto(totalPages - 1, ">>"));

        model.addAttribute("todoList", todoList);
        model.addAttribute("pages", pageNavList);
        model.addAttribute("filter", input);

        return "index";
    }

    @GetMapping("/page/form.do")
    public String getFormPage() {
        return "form";
    }

}
