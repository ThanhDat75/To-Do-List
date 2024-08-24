package com.example.todolist2.Repository;

import com.example.todolist2.Model.Item;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Set;

@Repository
public interface ItemRepo extends JpaRepository<Item, Integer> {
    Set<Item> getItemsByCreatedDateBetween(LocalDateTime date1, LocalDateTime date2, Sort sort);
}
