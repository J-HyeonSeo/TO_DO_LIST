package com.jhs.todolist.entity;

import com.jhs.todolist.dto.TodoDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "todo")
public class TodoEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DBMS에게 ID할당 위임.
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "checked")
    private boolean checked;

    public static TodoDto toDto(TodoEntity todoEntity) {
        return TodoDto.builder()
            .id(todoEntity.getId())
            .content(todoEntity.getContent())
            .checked(todoEntity.isChecked())
            .build();
    }

}
