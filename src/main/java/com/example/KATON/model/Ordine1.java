package com.example.KATON.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
@Setter
@Getter
public class Ordine1 {

    private String nomeTavolo;
    private int numeroTavolo;
    private int persone;
    private String cameriere;
    private String servitore;
    private boolean completato;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Piatto> piatti;

    public Ordine1(Ordine ordine){
        this.nomeTavolo = ordine.getNomeTavolo();
        this.numeroTavolo = ordine.getNumeroTavolo();
        this.cameriere = ordine.getCameriere().getNome();
        this.persone = ordine.getPersone();
        this.piatti=ordine.getPiatti();
        this.servitore=ordine.getServitore();
        this.completato=ordine.isCompletato();
    }
}
