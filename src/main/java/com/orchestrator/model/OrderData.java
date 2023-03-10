package com.orchestrator.model;

public class OrderData {

	private Long idProdotto;
	private int numeroPezzi;
	private Long idMagazzino;
	
	public Long getIdProdotto() {
		return idProdotto;
	}

	public void setIdProdotto(Long idProdotto) {
		this.idProdotto = idProdotto;
	}

	public int getNumeroPezzi() {
		return numeroPezzi;
	}

	public void setNumeroPezzi(int numeroPezzi) {
		this.numeroPezzi = numeroPezzi;
	}

	public Long getIdMagazzino() {
		return idMagazzino;
	}

	public void setIdMagazzino(Long idMagazzino) {
		this.idMagazzino = idMagazzino;
	}
	
}
