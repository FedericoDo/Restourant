package com.example.KATON.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@Entity
public class Ordine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int numeroTavolo;
	private String nomeTavolo;
	private int persone;
	private String servitore;

	private boolean completato;

	@ManyToOne
	@JoinColumn(name = "cameriere_id")
	private Cameriere cameriere;
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	private List<Piatto> piatti;


    public List<Piatto> getOrdine() {
		return piatti;
	}

	public Ordine() {
		super();
		piatti = new ArrayList<>();
		this.completato = false;
	}

    public double getTotale(){
		double tot=0;
		for (Piatto p:piatti){
			tot+=p.getPrezzo()*p.getQuantity();
		}
		tot+=persone*1.0;
		return tot;
	}
}