package Conexao;

import java.net.*;
import java.io.*;

public class Servidor {
	
	static ServerSocket servidor;
	static Socket socket;
	static DataOutputStream out;
	static DataInputStream in;
	static Users[] user = new Users[10];
	public static void main (String[] args) throws IOException{
		//teste bizarro loko
		int dano=20;
		int meuId=1;
		String exemplo = meuId+";dano;"+2+";"+dano;//mandou o exemplo
		String[] mensagem = exemplo.split(";");
		//recebeu e deu split
		if(mensagem[1].equals("dano")){//faz o codigo pra dar o dano
		}else if(mensagem[1].equals("move")){//faz o carinha x se mover
		}
		for (int i = 0; i < mensagem.length; i++) {
			System.out.println(mensagem[i]);
		}
		int danoteste = Integer.parseInt(mensagem[3]);
		danoteste*=2;
		System.out.println(danoteste+"");
		
		
		
		servidor = new ServerSocket(9090);
		System.out.println("Servidor Ok!!");
		while(true){
			socket = servidor.accept();
			for(int i=0;i<user.length;i++){
				if(user[i]==null){
					System.out.println("Cliente: "+i+" está conectado");
					out = new DataOutputStream(socket.getOutputStream());
					in = new DataInputStream(socket.getInputStream());	
					user[i]= new Users(out, in, user,i);
					Thread thread = new Thread(user[i]);
					thread.start();
					break;
				}
			
			}
		}
	}
}

class Users implements Runnable{
	DataOutputStream out;
	DataInputStream in;
	Users[] user = new Users[30];
	String name;
	int id;
	boolean isOn;
	public Users(DataOutputStream out, DataInputStream in, Users[] user,int id) {
		this.in = in;
		this.out = out;
		this.user=user;
		this.id=id;
		isOn=true;
	}
	
	public void run() {
		try {
			//coisas antes do loop
			name = in.readUTF();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(isOn){
			try {
				String message = in.readUTF();
				if(message.equals("exit")){//cliente desconectou
					isOn=false;
					System.out.println("Cliente: "+id+" desconectou");
				}else{
					System.out.println(id+";"+message);
					for (int i = 0; i < user.length; i++) {
						if(user[i]!=null && i!=id){
							user[i].out.writeUTF(id+";"+message);
						}
					}
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				user[id] = null;
				this.out=null;
				this.in=null;
				e.printStackTrace();
			}
		}
		user[id] = null;
		this.out=null;
		this.in=null;
		
	}
	
}
