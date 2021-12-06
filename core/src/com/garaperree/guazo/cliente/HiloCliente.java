package com.garaperree.guazo.cliente;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.garaperree.guazo.utiles.Global;

public class HiloCliente extends Thread {
	
	private DatagramSocket conexion;
	private InetAddress ipServer;
	private int puerto = 8080;
	
	boolean fin = false;
	
	public HiloCliente() {
		try {
			ipServer = InetAddress.getByName("255.255.255.255");
			conexion = new DatagramSocket();
		} catch (SocketException | UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(!fin) { 
			byte[] datos = new byte[1024];
			DatagramPacket dp = new DatagramPacket(datos, datos.length);
			try {
				conexion.receive(dp);
				procesarMensaje(dp);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void procesarMensaje(DatagramPacket dp) {
		String msg = new String(dp.getData()).trim();  
		if(msg.equals("Ok")) {
			ipServer = dp.getAddress();
		} else if(msg.equals("Empieza")) {
			Global.empieza = true; 
		}
	}
	
	public void enviarMensaje(String msg) {
		byte[] data = msg.getBytes();
		try {
			ipServer = InetAddress.getByName("192.168.1.50");
			DatagramPacket dp = new DatagramPacket(data, data.length, ipServer, puerto);
			conexion.send(dp);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
