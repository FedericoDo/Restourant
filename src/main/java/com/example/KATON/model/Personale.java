package com.example.KATON.model;

import org.springframework.boot.web.servlet.server.Session;

import java.util.HashMap;
import java.util.Map;



public class Personale {
	
	private Map<String,Ordine[]> tutto;
	private Map<String, Session> sessioni;
	
	public Map<String,Ordine[]> getTutto(){
		return tutto;
	}

	public Map<String, Session> getSessioni() {
		return sessioni;
	}
	
	public Personale() {
		super();
		tutto = new HashMap<String,Ordine[]>();
		sessioni = new HashMap<String, Session>();
	}
}