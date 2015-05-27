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
	private JLabel titre, solo, multi, lblSoloJ,lblSoloG,
	lblSoloRVD, lblSoloRCP, lblMultiJ,lblMultiG, lblMultiRVD, lblMultiRCP;
	
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
		this.addLabelSoloRatioVD();
		this.addLabelSoloRatioCP();
		this.addLabelMulti();
		this.addLabelMultiJoues();
		this.addLabelMultiGagnes();
		this.addLabelMultiRatioVD();
		this.addLabelMultiRatioCP();
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
		
	private void addLabelSoloJoues(){
		this.lblSoloJ = new JLabel("Matchs joués :");
		lblSoloJ.setSize(120, 20);
		lblSoloJ.setLocation(70, 190);
		lblSoloJ.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(lblSoloJ);
	}
		
	private void addLabelSoloGagnes(){
		this.lblSoloG = new JLabel("Gagnés :");
		lblSoloG.setSize(80, 20);
		lblSoloG.setLocation(70, 230);
		lblSoloG.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(lblSoloG);
	}
		
	private void addLabelSoloRatioVD(){
		this.lblSoloRVD = new JLabel("Ratio (victoires/défaites) :");
		lblSoloRVD.setSize(200, 20);
		lblSoloRVD.setLocation(70, 270);
		lblSoloRVD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(lblSoloRVD);
	}
	
	private void addLabelSoloRatioCP(){
		this.lblSoloRCP = new JLabel("Ratio (coups/parties) :");
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
	
	private void addLabelMultiRatioVD(){
		this.lblMultiRVD = new JLabel("Ratio (victoires/défaites) :");
		lblMultiRVD.setSize(200, 20);
		lblMultiRVD.setLocation(670, 270);
		lblMultiRVD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(lblMultiRVD);
	}
	
	private void addLabelMultiRatioCP(){
		this.lblMultiRCP = new JLabel("Ratio (coups/parties) :");
		lblMultiRCP.setSize(200, 20);
		lblMultiRCP.setLocation(670, 310);
		lblMultiRCP.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(lblMultiRCP);
	}
}
