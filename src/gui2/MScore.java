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
	
	private void addLabelTitre(){
		this.titre = new JLabel("Mes scores");
		titre.setFont(new Font("Agency FB", Font.PLAIN, 40));
		titre.setBounds(170, 50, 660, 100);
		titre.setVerticalAlignment( SwingConstants.TOP );
		titre.setHorizontalAlignment( SwingConstants.CENTER );
	    this.add(titre);
	}
	
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
		
	private void addLabelSolo(){
		this.solo = new JLabel("Solo :");
		solo.setSize(60, 20);
		solo.setLocation(100, 150);
		solo.setFont(new Font("Tahoma", Font.BOLD, 16));
		this.add(solo);
	}
		
	private void addLabelSoloJoues(int joues){
		this.lblSoloJ = new JLabel("Matchs joués : " + joues);
		lblSoloJ.setSize(130, 20);
		lblSoloJ.setLocation(70, 190);
		lblSoloJ.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(lblSoloJ);
	}
		
	private void addLabelSoloGagnes(int gagnes){
		this.lblSoloG = new JLabel("Gagnés : " + gagnes);
		lblSoloG.setSize(90, 20);
		lblSoloG.setLocation(70, 230);
		lblSoloG.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(lblSoloG);
	}
	
	private void addLabelSoloCoups(int coups){
		this.lblSoloC = new JLabel("Coups joués : " + coups);
		lblSoloC.setSize(150, 20);
		lblSoloC.setLocation(70, 270);
		lblSoloC.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(lblSoloC);
	}
		
	private void addLabelSoloRatioVD(double rvd){
		this.lblSoloRVD = new JLabel("Ratio (victoires/défaites) : " + rvd);
		lblSoloRVD.setSize(210, 20);
		lblSoloRVD.setLocation(70, 310);
		lblSoloRVD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(lblSoloRVD);
	}
	
	private void addLabelSoloRatioCP(double rcp){
		this.lblSoloRCP = new JLabel("Ratio (coups/parties) : " + rcp);
		lblSoloRCP.setSize(210, 20);
		lblSoloRCP.setLocation(70, 350);
		lblSoloRCP.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(lblSoloRCP);
	}
		
	private void addLabelMulti(){
		this.multi = new JLabel("Multijoueur :");
		multi.setSize(110, 20);
		multi.setLocation(700, 150);
		multi.setFont(new Font("Tahoma", Font.BOLD, 16));
		this.add(multi);
	}
		
	private void addLabelMultiJoues(int joues){
		this.lblMultiJ = new JLabel("Matchs joués : " + joues);
		lblMultiJ.setSize(130, 20);
		lblMultiJ.setLocation(670, 190);
		lblMultiJ.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(lblMultiJ);
	}
		
	private void addLabelMultiGagnes(int gagnes){
		this.lblMultiG = new JLabel("Gagnés : " + gagnes);
		lblMultiG.setSize(90, 20);
		lblMultiG.setLocation(670, 230);
		lblMultiG.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(lblMultiG);
	}
	
	private void addLabelMultiCoups(int coups){
		this.lblMultiC = new JLabel("Coups joués : " + coups);
		lblMultiC.setSize(150, 20);
		lblMultiC.setLocation(670, 270);
		lblMultiC.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(lblMultiC);
	}
	
	private void addLabelMultiRatioVD(double rvd){
		this.lblMultiRVD = new JLabel("Ratio (victoires/défaites) : " + rvd);
		lblMultiRVD.setSize(210, 20);
		lblMultiRVD.setLocation(670, 310);
		lblMultiRVD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(lblMultiRVD);
	}
	
	private void addLabelMultiRatioCP(double rcp){
		this.lblMultiRCP = new JLabel("Ratio (coups/parties) : " + rcp);
		lblMultiRCP.setSize(210, 20);
		lblMultiRCP.setLocation(670, 350);
		lblMultiRCP.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(lblMultiRCP);
	}
	
	public void clic(){
		if( fenetre.getClient().getJoueur() != null ){
			String login = fenetre.getClient().getJoueur().getIdentifiant();
			if( login != null && !login.equals("") ){
				Paquet p = Paquet.creeDEMANDE_STATS( );
				int id = p.getId();
				fenetre.getClient().envoyerPaquet(p);
				Paquet ps = fenetre.getClient().recevoirPaquet(5.0, id);
				if( ps != null ){
					int sj = (int)ps.getObjet(0);
					int sg = (int)ps.getObjet(1);
					int sc = (int)ps.getObjet(2);
					int mj = (int) ps.getObjet(3);
					int mg = (int) ps.getObjet(4);
					int mc = (int)ps.getObjet(5);
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
	
}
