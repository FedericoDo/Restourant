package com.example.KATON.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Piatto  {

    private int quantity;
    private String nome;
    private double prezzo;
    private String note;
    @Getter
    @Setter
    @Id
    @GeneratedValue
    private Long id;

    public Piatto(String nome, int quantity, double price, String note) {
        this.nome = nome ;
        this.prezzo=price;
        this.quantity = quantity;
        this.note = note;
    }
    public Piatto() {
        super();
    }

}
