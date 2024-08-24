package com.example.todolist2.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "item")
@Setter
@Getter
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemID;

    @Column(name = "description")
    private String description;

    @Column(name = "isDone")
    private boolean isDone;

    @Column(name = "createdDate")
    private LocalDateTime createdDate;

    @Column(name = "modifiedDate")
    private LocalDateTime modifiedDate;

    public Item(String description) {
        this.description = description;
        this.isDone = false;
        this.createdDate = LocalDateTime.now();
        this.modifiedDate = LocalDateTime.now();
    }

}
