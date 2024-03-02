package com.example.KATON.model;

import jakarta.persistence.*;
import lombok.ToString;
import org.hibernate.annotations.ManyToAny;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Entity
public class Ordine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int numeroTavolo;
	private String nomeTavolo;
	private int persone;
	private String servitore;

	@ManyToOne
	@JoinColumn(name = "cameriere_id")
	private Cameriere cameriere;
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	private List<Piatto> piatti;



	public int getNumeroTavolo() {
		return numeroTavolo;
	}

	public void setNumeroTavolo(int tavolo) {
		this.numeroTavolo = tavolo;
	}

	public String getServitore() {
		return servitore;
	}

	public void setServitore(String s) {
		this.servitore = s;
	}

	public String getNomeTavolo() {
		return nomeTavolo;
	}

	public void setNomeTavolo(String nomeTavolo) {
		this.nomeTavolo = nomeTavolo;
	}

	public int getPersone() {
		return persone;
	}

	public void setPersone(int persone) {
		this.persone = persone;
	}

	public List<Piatto> getOrdine() {
		return piatti;
	}

	public Ordine() {
		super();
		piatti = new ArrayList<Piatto>();
	}


	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public Cameriere getCameriere() {
		return cameriere;
	}

	public void setCameriere(Cameriere cameriere) {
		this.cameriere = cameriere;
	}

	public List<Piatto> getPiatti() {
		return piatti;
	}

	public void setPiatti(List<Piatto> piatti) {
		this.piatti = piatti;
	}
}