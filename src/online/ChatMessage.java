package online;


import java.io.*;
/* Cette classe définie les différents type de messages qui seront echangés entre les
 * Clients et le Serveur
 * Lorsqu'on parle depuis un Client Java vers un Serveur Java, c'est bien plus simple
 * de faire passer des objets Java. Il n'y a pas besoin de compter le nombre de bits ou
 * d'attendre qu'une ligne soit terminée à la fin d'une frame.
 */
public class ChatMessage implements Serializable {
	
	// ID unique obligatoire pour la sérialisation
	protected static final long serialVersionUID = 1112122200L;

	// QUIESTLA pour recevoir la liste des utilisateurs connectés
	// MESSAGE pour envoyer un message quelconque
	// DECO pour se déconnecter du serveur
	// JOUERAVEC pour demander de jouer avec un joueur
	// ACCEPTE pour accepter de jouer avec un joueur
	// LANCEMENT pour démarrer une partie
	static final int PARTIESDISPO = 0, MESSAGE = 1, DECO = 2, JOUERAVEC = 3, ACCEPTE = 4, LANCEMENT = 5, MESSAGEA = 6;
	private int type;
	private String message, destinataire;
	
	// Constructeur
	ChatMessage(int type, String destinataire, String message) {
		this.type = type;
		this.destinataire = destinataire;
		this.message = message;
	}
	
	
	// Les getters
	int getType() {
		return type;
	}
	
	String getMessage() {
		return message;
	}
	
	String getDestinataire() {
		return destinataire;
	}
}


