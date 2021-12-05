package com.garaperree.guazo.hilos;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class HiloServidor extends Thread{
	
	DatagramSocket socket;
	
	boolean fin = false;
	
	public HiloServidor() {
		try {
			socket = new DatagramSocket(8080);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(!fin) {
			byte[] datos = new byte[1024];
			DatagramPacket dp = new DatagramPacket(datos, datos.length);
			try {
				socket.receive(dp);
				procesarMensaje(dp);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void procesarMensaje(DatagramPacket dp) {
		String msg = new String(dp.getData()).trim();  
		System.out.println(msg);		
	}
	
	public void enviarMensaje(String msg) {
		byte[] data = msg.getBytes();
		InetAddress ipDestino;
		try {
			ipDestino = InetAddress.getByName("192.168.1.50");
			int puerto = 8080;
			DatagramPacket dp = new DatagramPacket(data, data.length);
			socket.send(dp);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
