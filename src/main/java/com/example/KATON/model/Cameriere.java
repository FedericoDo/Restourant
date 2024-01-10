package com.example.KATON.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="cameriere")
public class Cameriere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @OneToMany(mappedBy = "cameriere", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Ordine> ordini= new ArrayList<>();



    public Cameriere() {
        super();
        ordini = new ArrayList<Ordine>();
    }

    public String getNome() {
        return nome;
    }

    public List<Ordine> getOrdini() {
        return ordini;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setOrdini(List<Ordine> ordini) {
        this.ordini = ordini;
    }

    public Cameriere(String nome, List<Ordine> ordini) {
        this.nome = nome;
        this.ordini = ordini;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
