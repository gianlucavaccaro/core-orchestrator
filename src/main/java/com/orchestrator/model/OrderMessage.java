package com.orchestrator.model;

public class OrderMessage {
	
	private Long idOrder;
	private String tipologia;
	private Long idProdotto;
	private int numeroPezzi;
	
	public OrderMessage(Long idOrder, String tipologia, Long idProdotto, int numeroPezzi) {
		this.idOrder = idOrder;
		this.tipologia = tipologia;
		this.idProdotto = idProdotto;
		this.numeroPezzi = numeroPezzi;
	}

	public OrderMessage() {
		super();
	}

	public Long getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(Long idOrder) {
		this.idOrder = idOrder;
	}

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

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

	@Override
	public String toString() {
		return "OrderMessage [idOrder=" + idOrder + ", tipologia=" + tipologia + ", idProdotto=" + idProdotto
				+ ", numeroPezzi=" + numeroPezzi + "]";
	}
	
	

}
