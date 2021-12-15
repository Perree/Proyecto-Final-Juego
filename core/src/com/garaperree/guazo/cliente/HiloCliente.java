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
			ipServer = InetAddress.getByName("192.168.0.77");
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
		} while (!fin);
	}

	private void procesarMensaje(DatagramPacket dp) {
		String msg = (new String(dp.getData())).trim();

		String[] msgCompuesto = msg.split("!");

		if (msgCompuesto.length == 1) {
			if (msg.equals("Empieza")) {
				Utiles.listener.empieza();
			}
			if(msg.equals("seAcaboTiempoBrother")) {
				Utiles.listener.seAcaboElTiempo();
			}
		} else {

			// Se recibe si la conexion fue aceptada y se asigna nro de jugador
			if (msgCompuesto[0].equals("ConexionAceptada")) {
				Utiles.listener.asignarJugador(Integer.valueOf(msgCompuesto[1]));
			}

			// Se recibe la posicion X e Y del personaje
			if (msgCompuesto[0].equals("actualizarPos")) {
				if (msgCompuesto[1].equals("1")) {
					Utiles.listener.asignarPos(1, Float.parseFloat(msgCompuesto[2]), Float.parseFloat(msgCompuesto[3]));
				} else if (msgCompuesto[1].equals("2")) {
					Utiles.listener.asignarPos(2, Float.parseFloat(msgCompuesto[2]), Float.parseFloat(msgCompuesto[3]));
				}
			}

			// Se recibe el mensaje de que el juego termino y se le envia quien perdio
			if (msgCompuesto[0].equals("termino")) {
				Utiles.listener.terminoJuego(Integer.parseInt(msgCompuesto[1]));
			}

			// Se recibe el tiempo del hud para que vayan al mismo tiempo, este todavia esta
			// en prueba
			if (msgCompuesto[0].equals("tiempo")) {
				Utiles.listener.actualizarTiempo(Integer.parseInt(msgCompuesto[1]));
			}

			if (msgCompuesto[0].equals("finalizoCarrera")) {
				Utiles.listener.ganoJuego(Integer.parseInt(msgCompuesto[1]));
			}

			// Se recibe los frames del personaje
			if (msgCompuesto[0].equals("Animacion")) { // tratamos de hacer la animacion del personaje pero solo pudimos
														// conseguir que se mueva un frame
				if (msgCompuesto[1].equals("1")) {
					Utiles.listener.actualizarAnimacion(1, msgCompuesto[2]);
				} else if (msgCompuesto[1].equals("2")) {
					Utiles.listener.actualizarAnimacion(2, msgCompuesto[2]);
				}
			}
		}
	}
}
