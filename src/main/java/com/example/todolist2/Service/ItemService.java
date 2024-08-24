package com.example.todolist2.Service;

import com.example.todolist2.DTO.ItemDTO;
import com.example.todolist2.DTO.ItemUpdateDTO;
import com.example.todolist2.Model.Item;
import com.example.todolist2.Repository.ItemRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ItemService implements IItemService{
    @Autowired
    private ItemRepo itemRepo;

    @Override
    public List<Item> findAllItem() {
        return itemRepo.findAll();
    }

    @Override
    public Item getItemById(int itemID) throws EntityNotFoundException {
        return itemRepo.findById(itemID).get();
    }

    @Override
    public void addNewItem(ItemDTO itemDTO) {
        itemRepo.save(new Item(itemDTO.getDescription()));
    }

    @Override
    public Item updateItemById(int itemID, ItemUpdateDTO itemUpdateDTO) throws EntityNotFoundException {
        Item item = itemRepo.findById(itemID).get();
        if (itemUpdateDTO.getDescription() != null) {
            item.setDescription(itemUpdateDTO.getDescription());
        }
        item.setDone(itemUpdateDTO.getIsDone() == 1);
        item.setModifiedDate(LocalDateTime.now());
        itemRepo.save(item);
        return item;
    }

    @Override
    public Item deleteItemById(int itemID) {
        Item item = itemRepo.findById(itemID).get();
        itemRepo.delete(item);
        return item;
    }

    @Override
    public Set<Item> getItemCreatedBetween2Date(LocalDate date1, LocalDate date2) {
        Sort sort =Sort.by(Sort.Direction.DESC, "createdDate");
        if (date2.isAfter(date1)) {
            return itemRepo.getItemsByCreatedDateBetween(date1.atStartOfDay(), date2.plusDays(1).atStartOfDay(), sort);
        }
        return itemRepo.getItemsByCreatedDateBetween(date2.atStartOfDay(), date1.plusDays(1).atStartOfDay(), sort);
    }

    @Override
    public List<Item> getItemByStatus(boolean isDone) {
        List<Item> itemList = new ArrayList<>();
        for (Item item :
                itemRepo.findAll()) {
            if (item.isDone() == isDone) {
                itemList.add(item);
            }
        }
        return itemList;
    }
}
