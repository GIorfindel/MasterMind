package gui2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class MScore extends Menu{
	
	private Fenetre fenetre;
	private JLabel titre, solo, multi, lblSoloJ,lblSoloG, lblSoloR,lblMultiJ,lblMultiG, lblMultiR;
	
	public MScore( Fenetre fenetre ){
		this.fenetre = fenetre;
		this.init();
	}
	
	private void init(){
		this.setLayout( null );
		this.setBackground( Color.WHITE );
		this.addLabelTitre();
		this.addLabelSolo();
		this.addLabelSoloJoues();
		this.addLabelSoloGagnes();
		this.addLabelSoloRatio();
		this.addLabelMulti();
		this.addLabelMultiJoues();
		this.addLabelMultiGagnes();
		this.addLabelMultiRatio();
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
		solo.setLocation(300, 150);
		solo.setFont(new Font("Tahoma", Font.BOLD, 16));
		this.add(solo);
	}
		
	private void addLabelSoloJoues(){
		this.lblSoloJ = new JLabel("Matchs joués :");
		lblSoloJ.setSize(120, 20);
		lblSoloJ.setLocation(270, 190);
		lblSoloJ.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(lblSoloJ);
	}
		
	private void addLabelSoloGagnes(){
		this.lblSoloG = new JLabel("Gagnés :");
		lblSoloG.setSize(80, 20);
		lblSoloG.setLocation(270, 230);
		lblSoloG.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(lblSoloG);
	}
		
	private void addLabelSoloRatio(){
		this.lblSoloR = new JLabel("Ratio :");
		lblSoloR.setSize(70, 20);
		lblSoloR.setLocation(270, 270);
		lblSoloR.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(lblSoloR);
	}
		
	private void addLabelMulti(){
		this.multi = new JLabel("Multijoueur :");
		multi.setSize(110, 20);
		multi.setLocation(700, 150);
		multi.setFont(new Font("Tahoma", Font.BOLD, 16));
		this.add(multi);
	}
		
	private void addLabelMultiJoues(){
		this.lblMultiJ = new JLabel("Matchs joués :");
		lblMultiJ.setSize(120, 20);
		lblMultiJ.setLocation(670, 190);
		lblMultiJ.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(lblMultiJ);
	}
		
	private void addLabelMultiGagnes(){
		this.lblMultiG = new JLabel("Gagnés :");
		lblMultiG.setSize(80, 20);
		lblMultiG.setLocation(670, 230);
		lblMultiG.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(lblMultiG);
	}
		
	private void addLabelMultiRatio(){
		this.lblMultiR = new JLabel("Ratio :");
		lblMultiR.setSize(70, 20);
		lblMultiR.setLocation(670, 270);
		lblMultiR.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(lblMultiR);
	}
}
