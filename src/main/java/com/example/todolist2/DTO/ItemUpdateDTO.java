package com.example.todolist2.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemUpdateDTO {
    private String description;
    private int isDone;
}
