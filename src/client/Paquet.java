package client;

import java.io.Serializable;

public class Paquet implements Serializable{
	private static final long serialVersionUID = 1L;
	private Object[] objets;
	private int nbObjet;
	static final int DEMANDE_CONNECTION = 0, REPONSE_CONNECTION = 1 ;
	static final int DEMANDE_INSCRIPTION = 2, REPONSE_INSCRIPTION = 3 ;
	private int type;
	
	public Paquet( int nbObjet, int type ){
		this.objets = new Object[nbObjet];
		this.nbObjet = 0;
		this.type = type;
	}
	
	public Paquet( Paquet p ){
		this.nbObjet = p.nbObjet;
		this.type = p.type;
		this.objets = new Object[p.objets.length];
		for( int i = 0; i < p.objets.length; i++ ){
			this.objets[i] = p.objets[i];
		}
	}
	
	public void addObjet( Object objet ){
		this.objets[this.nbObjet] = objet;
		this.nbObjet ++;
	}
	
	public int getNbObjet(){
		return this.nbObjet;
	}
	
	public Object getObjet( int indice ){
		return this.objets[indice];
	}
	
	public int getType(){
		return this.type;
	}
	
	public void setType( int type ){
		this.type = type;
	}
}