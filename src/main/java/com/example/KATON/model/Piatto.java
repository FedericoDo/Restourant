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
    @Id
    @GeneratedValue
    private Long id;

    public Piatto(String nome, int quantity, double price, String note) {
        this.nome = nome ;
        this.prezzo=price;
        this.quantity = quantity;
        this.note = note;
    }

   /* public String[] getListaPiatti(){
        String[] res = new String[nomePiatto.values().length];
        int cont=0;
        for(nomePiatto p: nomePiatto.values()){
            res[cont]=p.toString();
            cont++;
        }
        return res;
    }*/
    public Piatto() {
        super();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
