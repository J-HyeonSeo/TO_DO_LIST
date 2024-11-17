package com.jhs.todolist.repository;

import com.jhs.todolist.entity.TodoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

    Page<TodoEntity> findAllByCheckedIsTrue(Pageable pageable);
    Page<TodoEntity> findAllByCheckedIsFalse(Pageable pageable);

}
