package com.garaperree.guazo.cliente;

public class Cliente {
	
	private HiloCliente hc;
		
	public Cliente() {
		hc = new HiloCliente();
		hc.start();
	}
	
	public void enviarMensaje(String msg) {
		this.hc.enviarMensaje(msg);
	}
}
