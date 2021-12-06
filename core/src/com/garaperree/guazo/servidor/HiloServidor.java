package com.garaperree.guazo.servidor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class HiloServidor extends Thread{
	
	private DatagramSocket conexion;
	
	private boolean fin = false;
	
	private DireccionRed[] clientes = new DireccionRed[2];
	
	private int cantClientes = 0;
	
	public HiloServidor() {
		try {
			conexion = new DatagramSocket(8080);
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
				conexion.receive(dp);
				procesarMensaje(dp);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void procesarMensaje(DatagramPacket dp) {
		String msg = new String(dp.getData()).trim(); 
		if(msg.equals("Conexion")) {
			if(cantClientes<2) {
				clientes[cantClientes++] = new DireccionRed(dp.getAddress(), dp.getPort());
				enviarMensaje("Ok", clientes[cantClientes].getIp(), clientes[cantClientes++].getPuerto());
			} else if(cantClientes==2) {
				for(int i = 0; i < clientes.length; i++) {
					enviarMensaje("Empieza", clientes[i].getIp(), clientes[i].getPuerto());
				}
			}
		}		
	}
	
	public void enviarMensaje(String msg, InetAddress ip, int puerto) {
		byte[] data = msg.getBytes();
		InetAddress ipDestino;
		try {
			ipDestino = InetAddress.getByName("192.168.1.50");
			DatagramPacket dp = new DatagramPacket(data, data.length, puerto);
			conexion.send(dp);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
