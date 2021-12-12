package com.garaperree.guazo.cliente;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.garaperree.guazo.utiles.Utiles;

public class HiloCliente extends Thread {
	
	private DatagramSocket conexion;
	private InetAddress ipServer;
	private boolean fin = false;
	private int puertoServer; 
	
public HiloCliente() {
		
		try {
			ipServer = InetAddress.getByName("192.168.0.47");
			puertoServer = 8080;
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		
		try {
			conexion = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	public void enviarMensaje(String msg) {
		byte[] data = msg.getBytes();
		DatagramPacket dp = new DatagramPacket(data, data.length, ipServer, puertoServer);
		try {
			System.out.println("Enviando Mensaje "+ msg + " ip " + ipServer + " puerto " + puertoServer);
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
				procesarMensaje(dp);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}while(!fin);
	}

	private void procesarMensaje(DatagramPacket dp) {
		String msg = (new String(dp.getData())).trim();
		
		String[] msgCompuesto = msg.split("!");
		
		if(msgCompuesto.length==1) {
			if(msg.equals("Empieza")) {
				Utiles.listener.empieza();
			}	
		} else {
			
			if(msgCompuesto[0].equals("ConexionAceptada")) {
				Utiles.listener.asignarJugador(Integer.valueOf(msgCompuesto[1]));
			}
			
			if(msgCompuesto[0].equals("coordenadas")) {
				if(msgCompuesto[1].equals("p1")) {
					Utiles.listener.asignarCoordenadas(1,Float.parseFloat(msgCompuesto[2]));
				} else if(msgCompuesto[1].equals("p2")) {
					Utiles.listener.asignarCoordenadas(2,Float.parseFloat(msgCompuesto[2]));
				}
			}
		}
	}
}
