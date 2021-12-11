package com.garaperree.guazo.cliente;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.garaperree.guazo.pantallas.PantallaJuego;
import com.garaperree.guazo.utiles.Global;

public class HiloCliente extends Thread {
	
	private DatagramSocket conexion;
	private InetAddress ipServer;
	private int puerto = 8880;
	boolean fin = false;
	private PantallaJuego app;
	
	public HiloCliente(PantallaJuego app) {
		this.app = app;
		try {
			ipServer = InetAddress.getByName("192.168.0.47");
			conexion = new DatagramSocket();
		} catch (SocketException | UnknownHostException e) {
			e.printStackTrace();
		}
		enviarMensaje("Conexion");
	}
	
	public void enviarMensaje(String msg) {
		byte[] data = msg.getBytes();
		DatagramPacket dp = new DatagramPacket(data, data.length, ipServer, puerto);
		try {
			System.out.println("Enviando Mensaje "+ msg + " ip " + ipServer + " puerto " + puerto);
			conexion.send(dp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
	
	@Override
	public void run() {
		do { 
			byte[] data = new byte[1024];
			DatagramPacket dp = new DatagramPacket(data, data.length);
			try {
				conexion.receive(dp);	
			} catch (IOException e) {
				e.printStackTrace();
			}
			procesarMensaje(dp);
		}while(!fin);
	}

	private void procesarMensaje(DatagramPacket dp) {
		String msg = (new String(dp.getData())).trim();
		
		String[] msgCompuesto = msg.split("-");
		
		if(msgCompuesto.length<2) {
			if(msg.equals("Ok")) {
				ipServer = dp.getAddress();
			} else if(msg.equals("Empieza")) {
				System.out.println("Llega EMPIEZA");
				Global.empieza = true; 
			}
		} else {
			if(msgCompuesto[0].equals("Actualizar")) {
				if(msgCompuesto[1].equals("P1")) {
					float posY = Float.parseFloat(msgCompuesto[2]);
					app.getJugador1().setY(posY);
				}
			}
		}
		
	}
}
