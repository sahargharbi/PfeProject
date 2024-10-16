package com.smarty.system.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction extends AbstractEntity {


    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private String destinationIban;
    private String note;

    @Column(updatable = false)
    private LocalDate transactionDate;



    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;
}