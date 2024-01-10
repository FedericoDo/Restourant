package com.example.KATON.model;


import org.springframework.boot.web.servlet.server.Session;

public class Req {
	
	private String req;
	private Ordine ord;
	private String cameriere;
	private Session session;

	public String getReq() {
		return req;
	}

	public void setReq(String req) {
		this.req = req;
	}
	public String getCameriere() {
		return cameriere;
	}

	public void setCameriere(String cameriere) {
		this.cameriere = cameriere;
	}

	public Ordine getOrd() {
		return ord;
	}

	public void setOrd(Ordine ord) {
		this.ord = ord;
	}
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Req() {
		super();
		ord = new Ordine();
	}
	
}