package com.example.KATON.model;

import java.util.HashMap;
import java.util.Map;

public class DbPiatti {
	
	private Map<String, Double> prezzario;

	public Map<String, Double> getPrezzario() {
		return prezzario;
	}

	public DbPiatti() {
		super();
		prezzario = new HashMap<String, Double>();
		prezzario.put("coperto", 1.5);
		prezzario.put("Pasta", 5.0);
		prezzario.put("Carne", 7.65);
	}
}