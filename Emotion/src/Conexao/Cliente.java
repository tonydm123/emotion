package Conexao;
import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Cliente {
	static Socket socket;
	static DataInputStream in;
	static DataOutputStream out;
	Input input;
	Thread thread;
	
	
	public Cliente(String ip,int porta) throws UnknownHostException, IOException {
		// TODO Auto-generated constructor stub
		socket = new Socket(ip,porta);
		System.out.println("Cliente conectado");
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());
		input = new Input(in);
		thread = new Thread(input);
		thread.start();
	}
	
	public void close(){
		input.isOn=false;
		try {
			out.writeUTF("exit");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String message){
		try {
			out.writeUTF(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Mensagem não enviada.");
		}
	}
	
	public String getMessage(){
		return input.getMessage();
	}
	
	public static void main (String[] args) throws UnknownHostException, IOException{
		socket = new Socket("10.3.62.206",9094);
		System.out.println("Cliente conectado");
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());
		Input input = new Input(in);
		Thread thread = new Thread(input);
		Scanner sc = new Scanner(System.in);
		thread.start();
		System.out.println("Digite seu nome");
		String name = sc.nextLine();
		out.writeUTF(name);
			while(true){
				//envia as mensagens constantemente
				String sendMessage = sc.nextLine();
				out.writeUTF(sendMessage);
			}
		}
		
	}
	


class Input implements Runnable{
	String message;
	DataInputStream in;
	boolean isOn;
	public Input(DataInputStream in) {
		this.in=in;
		message=null;
		isOn=true;
	}
	
	public String getMessage(){
		if(message!=null){
			String get = new String(message);
			message=null;
			return get;
		}else{
			return null;
		}
		
	}
	
	public void run() {
		while(isOn){
			try {
				//recebe as informações do server
				message = in.readUTF();
				System.out.println(message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("Desconectado.");
			}
		}
	}
	
}

