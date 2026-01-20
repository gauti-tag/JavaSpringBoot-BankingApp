package com.gauti.banking.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
@Data
@SuperBuilder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Role extends AbstractEntity {

    private String name; 

    @OneToOne
    @JoinColumn(name="id_user")
    private User user; 

}
