package gui2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import mastermind.Joueur;
import mastermind.Paquet;

@SuppressWarnings("serial")
public class MScore extends Menu{
	
	private Fenetre fenetre;
	private JLabel titre, solo, multi, lblSoloJ,lblSoloG, lblSoloC,
	lblSoloRVD, lblSoloRCP, lblMultiJ,lblMultiG, lblMultiC, lblMultiRVD, lblMultiRCP;
	
	public MScore( Fenetre fenetre ){
		this.fenetre = fenetre;
		this.init();
	}
	
	private void init(){
		this.setLayout( null );
		this.setBackground( Color.WHITE );
		this.addLabelTitre();
		this.addLabelSolo();
		this.addLabelMulti();
		this.addBoutonRetour();
	}
	
	/*
	 * Fonction qui instancie le label contenant le tire et l'ajoute a la page 
	 */
	private void addLabelTitre(){
		this.titre = new JLabel("Mes scores");
		titre.setFont(new Font("Agency FB", Font.PLAIN, 40));
		titre.setBounds(170, 50, 660, 100);
		titre.setVerticalAlignment( SwingConstants.TOP );
		titre.setHorizontalAlignment( SwingConstants.CENTER );
	    this.add(titre);
	}
	
	/*
	 * Fonction qui ajoute un bouton retour pour retourner à l'accueil
	 */
	private void addBoutonRetour(){
		JButton btn = new JButton( "Retour" );
		btn.setBounds(405, 400, 200, 50);
		btn.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){				
		        fenetre.showMenu( Fenetre.ACCUEIL );
		      }
		    });
		this.add( btn );
	}
		
	/*
	 * Fonction qui instancie et ajoute le label des scores en mode solo
	 */
	private void addLabelSolo(){
		this.solo = new JLabel("Solo :");
		solo.setSize(60, 20);
		solo.setLocation(100, 150);
		solo.setFont(new Font("Tahoma", Font.BOLD, 16));
		this.add(solo);
	}
		
	/*
	 * Fonction qui instancie et ajoute le label du nombre de parties jouées en solo
	 */
	private void addLabelSoloJoues(int joues){
		this.lblSoloJ = new JLabel("Matchs joués : " + joues);
		lblSoloJ.setSize(200, 20);
		lblSoloJ.setLocation(70, 190);
		lblSoloJ.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(lblSoloJ);
	}
		
	/*
	 * Fonction qui instancie et ajoute le label du nombre de parties gagnées en solo
	 */
	private void addLabelSoloGagnes(int gagnes){
		this.lblSoloG = new JLabel("Gagnés : " + gagnes);
		lblSoloG.setSize(200, 20);
		lblSoloG.setLocation(70, 230);
		lblSoloG.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(lblSoloG);
	}
	
	/*
	 * Fonction qui instancie et ajoute le label du nombre total de coups joués en solo
	 */
	private void addLabelSoloCoups(int coups){
		this.lblSoloC = new JLabel("Coups joués : " + coups);
		lblSoloC.setSize(200, 20);
		lblSoloC.setLocation(70, 270);
		lblSoloC.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(lblSoloC);
	}
		
	/*
	 * Fonction qui instancie et ajoute le label du ratio victoires/défaites en solo
	 */
	private void addLabelSoloRatioVD(double rvd){
		this.lblSoloRVD = new JLabel("Ratio (victoires/défaites) : " + rvd);
		lblSoloRVD.setSize(300, 20);
		lblSoloRVD.setLocation(70, 310);
		lblSoloRVD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(lblSoloRVD);
	}
	
	/*
	 * Fonction qui instancie et ajoute le label du ratio coups/parties en solo
	 */
	private void addLabelSoloRatioCP(double rcp){
		this.lblSoloRCP = new JLabel("Ratio (coups/parties) : " + rcp);
		lblSoloRCP.setSize(300, 20);
		lblSoloRCP.setLocation(70, 350);
		lblSoloRCP.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(lblSoloRCP);
	}
		
	/*
	 * Fonction qui instancie et ajoute le label des scores en mode multi
	 */
	private void addLabelMulti(){
		this.multi = new JLabel("Multijoueur :");
		multi.setSize(200, 20);
		multi.setLocation(700, 150);
		multi.setFont(new Font("Tahoma", Font.BOLD, 16));
		this.add(multi);
	}
	
	/*
	 * Fonction qui instancie et ajoute le label du nombre de parties jouées en multi
	 */	
	private void addLabelMultiJoues(int joues){
		this.lblMultiJ = new JLabel("Matchs joués : " + joues);
		lblMultiJ.setSize(200, 20);
		lblMultiJ.setLocation(670, 190);
		lblMultiJ.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(lblMultiJ);
	}
		
	/*
	 * Fonction qui instancie et ajoute le label du nombre de parties gagnées en multi
	 */	
	private void addLabelMultiGagnes(int gagnes){
		this.lblMultiG = new JLabel("Gagnés : " + gagnes);
		lblMultiG.setSize(150, 20);
		lblMultiG.setLocation(670, 230);
		lblMultiG.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(lblMultiG);
	}
	
	/*
	 * Fonction qui instancie et ajoute le label du nombre total de coups joués en multi
	 */
	private void addLabelMultiCoups(int coups){
		this.lblMultiC = new JLabel("Coups joués : " + coups);
		lblMultiC.setSize(200, 20);
		lblMultiC.setLocation(670, 270);
		lblMultiC.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(lblMultiC);
	}
	
	/*
	 * Fonction qui instancie et ajoute le label du ratio victoires/défaites en multi
	 */
	private void addLabelMultiRatioVD(double rvd){
		this.lblMultiRVD = new JLabel("Ratio (victoires/défaites) : " + rvd);
		lblMultiRVD.setSize(300, 20);
		lblMultiRVD.setLocation(670, 310);
		lblMultiRVD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(lblMultiRVD);
	}
	
	/*
	 * Fonction qui instancie et ajoute le label du ratio coups/parties en multi
	 */
	private void addLabelMultiRatioCP(double rcp){
		this.lblMultiRCP = new JLabel("Ratio (coups/parties) : " + rcp);
		lblMultiRCP.setSize(300, 20);
		lblMultiRCP.setLocation(670, 350);
		lblMultiRCP.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(lblMultiRCP);
	}
	
	/*
	 * Fonction qui n'est exécutée que lors du clic sur le bouton scores 
	 * dans la page accueil afin de n'envoyer les paquets suelement
	 *  si le joueur est connecté à son compte
	 */
	public void clic(){
		if( fenetre.getClient().getJoueur() != null ){
			
			//On récupére l'identifiant de l'utilisateur
			String login = fenetre.getClient().getJoueur().getIdentifiant();
			if( login != null && !login.equals("") ){
				
				//On demande les stats du joueur connecté
				Paquet p = Paquet.creeDEMANDE_STATS( login );
				int id = p.getId();
				fenetre.getClient().envoyerPaquet(p);
				//On récupére les stats
				Paquet ps = fenetre.getClient().recevoirPaquet(5.0, id);
				if( ps != null ){
					/*
					 * On stoque les stats dans des variables puis on calcul les ratios 
					 * et on instancie les labels
					 */
					int sj = ((Integer)ps.getObjet(0)).intValue();
					int sg = ((Integer)ps.getObjet(1)).intValue();
					int sc = ((Integer)ps.getObjet(2)).intValue();
					int mj = ((Integer) ps.getObjet(3)).intValue();
					int mg = ((Integer) ps.getObjet(4)).intValue();
					int mc = ((Integer)ps.getObjet(5)).intValue();
						this.addLabelSoloJoues(sj);
						this.addLabelSoloGagnes(sg);
						this.addLabelSoloCoups(sc);
						if (sg!=0 && sj!=0)
							this.addLabelSoloRatioVD((double)sg/(double)sj);
						else 
							this.addLabelSoloRatioVD(0.0);
						if (sc!=0 && sj!=0)
							this.addLabelSoloRatioCP((double)sc/(double)sj);
						else
							this.addLabelSoloRatioCP(0.0);
						this.addLabelMultiJoues(mj);
						this.addLabelMultiGagnes(mg);
						this.addLabelMultiCoups(mc);
						if (mg!=0 && mj!=0)
							this.addLabelMultiRatioVD((double)mg/(double)mj);
						else 
							this.addLabelMultiRatioVD(0.0);
						if (mc!=0 && mj!=0)
							this.addLabelMultiRatioCP((double)mc/(double)mj);
						else 
							this.addLabelMultiRatioCP(0.0);
				}
			}
		}
	}
	
	/*
	 * Fonction appelée en cas de déconnexion au serveur, le joueur est redirigé vers l'accueil
	 */
	public void decoServeur(){
		this.fenetre.showMenu( Fenetre.ACCUEIL );
	}
	
}
