package com.smarty.system.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Role extends AbstractEntity {


    private String name;

    @OneToOne
    @JoinColumn(name= "id_user")
    private User user ;
}
