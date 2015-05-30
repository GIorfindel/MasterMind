package gui2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import mastermind.Paquet;

@SuppressWarnings("serial")
public class Classement extends Menu {
	private Fenetre fenetre;
	private JLabel titre, lSolo, lMulti;
	private JTable solo, multi;
	
	public Classement (Fenetre fenetre){
		this.fenetre = fenetre;
		this.init();
	}
	
	private void init(){
		this.setLayout(null);
		this.addLabelTitre();
		this.addBoutonRetour();
		this.addLabelSolo();
		this.addLabelMulti();
	}
	
	private void addLabelTitre(){
		this.titre = new JLabel("Classement");
		titre.setFont(new Font("Agency FB", Font.PLAIN, 40));
		titre.setBounds(405, 50, 660, 100);
	    this.add(titre);
	}
	
	private void addBoutonRetour(){
		JButton btn = new JButton( "Retour" );
		btn.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){				
		        fenetre.showMenu( Fenetre.ACCUEIL );
		      }
		    });
		btn.setBounds(405, 550, 200, 50);
		this.add( btn );
	}
	
	private void addLabelSolo()
	{
		this.lSolo = new JLabel("Solo :");
		this.lSolo.setBounds(100,150, 50, 50);
		this.lSolo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(this.lSolo);
	}
	
	private void addLabelMulti()
	{
		this.lMulti = new JLabel("Multi :");
		this.lMulti.setBounds(100,300, 50, 90);
		this.lMulti.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(this.lMulti);
	}
	
	private void addTableaux()
	{
		Paquet joueursd = Paquet.creeDEMANDE_CLASSEMENT( );
		int id = joueursd.getId();
		fenetre.getClient().envoyerPaquet(joueursd);
		Paquet joueurss = fenetre.getClient().recevoirPaquet(20.0, id);
		DefaultTableModel model = new DefaultTableModel()
			{ public boolean isCellEditable(int row, int column){  
				return false;  
			}
		};
		DefaultTableModel model2 = new DefaultTableModel()
			{ public boolean isCellEditable(int row, int column){  
				return false;  
			}
		};
		
		this.solo = new JTable(model);
		solo.setAutoCreateRowSorter(true);
		JScrollPane scrollPane = new JScrollPane(solo);
		model.addColumn("Identifiant");
		model.addColumn("Joués");
		model.addColumn("Gagnés");
		model.addColumn("Coups");
		model.addColumn("victoires/défaites");
		model.addColumn("coups/parties");
			    
		this.multi = 
		new JTable(model2);
		multi.setAutoCreateRowSorter(true);
		JScrollPane scrollPane2 = new JScrollPane(multi);
		model2.addColumn("Identifiant");
		model2.addColumn("Joués");
		model2.addColumn("Gagnés");
		model2.addColumn("Coups");
		model2.addColumn("victoires/défaites");
		model2.addColumn("coups/parties");
		
		for (int i =0; i<joueurss.getNbObjet(); i++)
		{
				String login = (String) joueurss.getObjet(i);
				if( login != null && !login.equals("") ){
					Paquet p = Paquet.creeDEMANDE_STATS( login );
					int id1 = p.getId();
					fenetre.getClient().envoyerPaquet(p);
					Paquet ps = fenetre.getClient().recevoirPaquet(5.0, id1);
					if( ps != null ){
						int sj = ((Integer)ps.getObjet(0)).intValue();
						int sg = ((Integer)ps.getObjet(1)).intValue();
						int sc = ((Integer)ps.getObjet(2)).intValue();
						int mj = ((Integer) ps.getObjet(3)).intValue();
						int mg = ((Integer) ps.getObjet(4)).intValue();
						int mc = ((Integer)ps.getObjet(5)).intValue();
						double srvd = 0.0;
						double srcp = 0.0;
						double mrvd = 0.0;
						double mrcp = 0.0;
						if (sg!=0 && sj!=0)
							srvd = ((double)sg/(double)sj);
						if (sc!=0 && sj!=0)
							srcp = ((double)sc/(double)sj);
						if (mg!=0 && mj!=0)
							mrvd = ((double)mg/(double)mj);
						if (mc!=0 && mj!=0)
							mrcp = ((double)mc/(double)mj);
						model.addRow(new Object[] {login, sj, sg, sc, srvd, srcp });
						model2.addRow(new Object[] {login, mj, mg, mc, mrvd, mrcp });
					}
				}
			}
		scrollPane.setBounds(100, 200, 800, 100);
		scrollPane2.setBounds(100, 370, 800,100);
		this.add(scrollPane);
		this.add(scrollPane2);
			
	}
	
	public void clic()
	{
		this.addTableaux();
	}
	
	public void decoServeur(){
		this.fenetre.showMenu( Fenetre.ACCUEIL );
	}
	
}
