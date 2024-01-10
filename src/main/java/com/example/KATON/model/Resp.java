package com.example.KATON.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Resp {
	
	private String tipo;
    private List<String> camerieri;
    private Ordine[] tavoli;
    private Map<String, Ordine[]> status;
    
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public List<String> getCamerieri() {
		return camerieri;
	}
	public Ordine[] getTavoli() {
		return tavoli;
	}
	public void setTavoli(Ordine[] tavoli) {
		this.tavoli = tavoli;
	}
	public Map<String,Ordine[]> getStatus() {
		return status;
	}
	public void setStatus( Map<String,Ordine[]>  status) {
		this.status = status;
	}
	public Resp() {
		super();
		camerieri = new ArrayList<String>();
		tavoli = new Ordine[0];
		status = new  HashMap<String,Ordine[]> ();
	} 
}