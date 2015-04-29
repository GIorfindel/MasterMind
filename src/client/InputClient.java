package client;

import java.io.InputStream;
import java.util.Scanner;

public class InputClient extends Thread{
	private Scanner input;
	private String inputS;
	private boolean continuer;
	
	public InputClient(){
		this.input = null;
		this.inputS = "";
		this.continuer = false;
	}
	
	public void run(){
		String s;
		this.input = new Scanner( System.in );
		this.continuer = true;
		while( this.continuer ){
			s = this.input.nextLine();
			this.inputS = s;
		}
		
	}
	
	public String getInputS(){
		if( this.inputS != null ){
			return new String( this.inputS );
		}
		return null;
	}
	
	public void close(){
		this.continuer = false;
		this.input.close();
		
	}
	
	public void viderBuffer(){
		this.close();
		this.start();
	}
}
