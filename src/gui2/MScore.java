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
	private JLabel titre, solo, multi, lblSoloJ,lblSoloG,
	lblSoloRVD, lblSoloRCP, lblMultiJ,lblMultiG, lblMultiRVD, lblMultiRCP;
	
	public MScore( Fenetre fenetre ){
		this.fenetre = fenetre;
		this.init();
	}
	
	private void init(){
		this.setLayout( null );
		this.setBackground( Color.WHITE );
		/*
		if( fenetre.getClient().getJoueur() != null ){
			String login = fenetre.getClient().getJoueur().getIdentifiant();
			if( login != null && !login.equals("") ){
				Paquet p = Paquet.creeDEMANDE_STATS( login );
				int id = p.getId();
				fenetre.getClient().envoyerPaquet(p);
				Paquet ps = fenetre.getClient().recevoirPaquet(5.0, id);
				if( ps != null ){
						this.addLabelSoloJoues((float) ps.getObjet(0));
						this.addLabelSoloGagnes((float) ps.getObjet(1));
						this.addLabelSoloRatioVD((float) ps.getObjet(2));
						this.addLabelSoloRatioCP((float) ps.getObjet(3));
						this.addLabelMultiJoues((float) ps.getObjet(4));
						this.addLabelMultiGagnes((float) ps.getObjet(5));
						this.addLabelMultiRatioVD((float) ps.getObjet(6));
						this.addLabelMultiRatioCP((float) ps.getObjet(7));
			}
		}
		}*/
		this.addLabelTitre();
		this.addLabelSolo();
		this.addLabelSoloJoues((float) 0);
		this.addLabelSoloGagnes((float) 0);
		this.addLabelSoloRatioVD((float) 0);
		this.addLabelSoloRatioCP((float) 0);
		this.addLabelMultiJoues((float) 0);
		this.addLabelMultiGagnes((float) 0);
		this.addLabelMultiRatioVD((float) 0);
		this.addLabelMultiRatioCP((float) 0);
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
		btn.setBounds(405, 350, 200, 50);
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
		
	private void addLabelSoloJoues(Float joues){
		this.lblSoloJ = new JLabel("Matchs joués : " + joues);
		lblSoloJ.setSize(120, 20);
		lblSoloJ.setLocation(70, 190);
		lblSoloJ.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(lblSoloJ);
	}
		
	private void addLabelSoloGagnes(Float gagnes){
		this.lblSoloG = new JLabel("Gagnés : " + gagnes);
		lblSoloG.setSize(80, 20);
		lblSoloG.setLocation(70, 230);
		lblSoloG.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(lblSoloG);
	}
		
	private void addLabelSoloRatioVD(Float rvd){
		this.lblSoloRVD = new JLabel("Ratio (victoires/défaites) : " + rvd);
		lblSoloRVD.setSize(200, 20);
		lblSoloRVD.setLocation(70, 270);
		lblSoloRVD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(lblSoloRVD);
	}
	
	private void addLabelSoloRatioCP(Float rcp){
		this.lblSoloRCP = new JLabel("Ratio (coups/parties) : " + rcp);
		lblSoloRCP.setSize(200, 20);
		lblSoloRCP.setLocation(70, 310);
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
		
	private void addLabelMultiJoues(Float joues){
		this.lblMultiJ = new JLabel("Matchs joués : " + joues);
		lblMultiJ.setSize(120, 20);
		lblMultiJ.setLocation(670, 190);
		lblMultiJ.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(lblMultiJ);
	}
		
	private void addLabelMultiGagnes(Float gagnes){
		this.lblMultiG = new JLabel("Gagnés : " + gagnes);
		lblMultiG.setSize(80, 20);
		lblMultiG.setLocation(670, 230);
		lblMultiG.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(lblMultiG);
	}
	
	private void addLabelMultiRatioVD(Float rvd){
		this.lblMultiRVD = new JLabel("Ratio (victoires/défaites) : " + rvd);
		lblMultiRVD.setSize(200, 20);
		lblMultiRVD.setLocation(670, 270);
		lblMultiRVD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(lblMultiRVD);
	}
	
	private void addLabelMultiRatioCP(Float rcp){
		this.lblMultiRCP = new JLabel("Ratio (coups/parties) : " + rcp);
		lblMultiRCP.setSize(200, 20);
		lblMultiRCP.setLocation(670, 310);
		lblMultiRCP.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(lblMultiRCP);
	}
	
}
