package com.smarty.system.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Task extends AbstractEntity{
    private Integer userId;
    private Date dateDebut;
    private Date dateFin;
    private String title;
    private String Description;

    @Enumerated(EnumType.STRING)
    private TaskDomain domain;





    @ManyToMany
    @JoinTable(
            name = "user_task",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users;

    @OneToMany(mappedBy = "task")
    private List<Transaction> transactions;
}