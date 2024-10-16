package com.smarty.system.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
@Entity

public class Account extends AbstractEntity {



    private String iban;



    @OneToOne
    @JoinColumn(name="id_user")
    private User user ;
}
