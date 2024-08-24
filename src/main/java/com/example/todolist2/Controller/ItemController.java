package com.example.todolist2.Controller;

import com.example.todolist2.DTO.ItemDTO;
import com.example.todolist2.DTO.ItemUpdateDTO;
import com.example.todolist2.Service.ItemService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "itemcontroller/")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping(value = "/findAllItem")
    public ResponseEntity<?> findAllItem() {
        return ResponseEntity.ok(itemService.findAllItem());
    }

    @GetMapping(value = "/finditembyid")
    public ResponseEntity<?> findItemByID(@RequestParam int itemID) {
        try {
            return ResponseEntity.ok(itemService.getItemById(itemID));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found!");
        }
    }

    @PostMapping(value = "/newItem", produces = MediaType.APPLICATION_JSON_VALUE)
    public String addNewItem(@RequestBody ItemDTO itemDTO) {
        itemService.addNewItem(itemDTO);
        return ("Done!");
    }
    @PutMapping(value = "/updateitem", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateItem(@RequestParam int itemID, @RequestBody ItemUpdateDTO itemUpdateDTO){
        try {
            return ResponseEntity.ok(itemService.updateItemById(itemID, itemUpdateDTO));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found!");
        }
    }

    @DeleteMapping(value = "/deleteitem")
    public ResponseEntity<?> deleteItem(@RequestParam int itemID) {
        try {
            return ResponseEntity.ok(itemService.deleteItemById(itemID));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found!");
        }
    }

    @GetMapping(value = "/getitembetween2dates")
    public ResponseEntity<?> getItemBetween2Dates(@RequestParam LocalDate date1, @RequestParam LocalDate date2) {
        return ResponseEntity.ok(itemService.getItemCreatedBetween2Date(date1, date2));
    }

    @GetMapping(value = "/getitembystatus")
    public ResponseEntity<?> getItemByStatus(@RequestParam boolean isDone) {
        return ResponseEntity.ok(itemService.getItemByStatus(isDone));
    }
}
