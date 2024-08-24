package com.example.todolist2.Service;

import com.example.todolist2.DTO.ItemDTO;
import com.example.todolist2.DTO.ItemUpdateDTO;
import com.example.todolist2.Model.Item;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface IItemService {
    List<Item> findAllItem();
    Item getItemById(int itemID);
    void addNewItem(ItemDTO itemDTO);
    Item updateItemById(int itemID, ItemUpdateDTO itemUpdateDTO);
    Item deleteItemById(int itemID);
    Set<Item> getItemCreatedBetween2Date(LocalDate date1, LocalDate date2);
    List<Item> getItemByStatus(boolean isDone);
}
