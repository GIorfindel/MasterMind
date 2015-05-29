package gui2;

import java.awt.BorderLayout;
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
	private JLabel titre;
	private JTable solo, multi;
	
	public Classement (Fenetre fenetre){
		this.fenetre = fenetre;
		this.init();
	}
	
	private void init(){
		this.setLayout(new BorderLayout());
		this.addLabelTitre();
		this.addBoutonRetour();
	}
	
	private void addLabelTitre(){
		this.titre = new JLabel("Classement");
		titre.setFont(new Font("Agency FB", Font.PLAIN, 40));
		titre.setBounds(170, 50, 660, 100);
		titre.setVerticalAlignment( SwingConstants.TOP );
		titre.setHorizontalAlignment( SwingConstants.CENTER );
	    this.add(titre, BorderLayout.NORTH);
	}
	
	private void addBoutonRetour(){
		JButton btn = new JButton( "Retour" );
		btn.setSize(200, 50);
		btn.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){				
		        fenetre.showMenu( Fenetre.ACCUEIL );
		      }
		    });
		this.add( btn , BorderLayout.SOUTH);
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
		model.addColumn("identifiant");
		model.addColumn("Matchs joués");
		model.addColumn("Matchs gagnés");
		model.addColumn("Coups joués");
		model.addColumn("Ratio (victoires/défaites)");
		model.addColumn("Ratio (coups/parties)");
			    
		this.multi = 
		new JTable(model2);
		multi.setAutoCreateRowSorter(true);
		JScrollPane scrollPane2 = new JScrollPane(multi);
		model2.addColumn("identifiant");
		model2.addColumn("Matchs joués");
		model2.addColumn("Matchs gagnés");
		model2.addColumn("Coups joués");
		model2.addColumn("Ratio (victoires/défaites)");
		model2.addColumn("Ratio (coups/parties)");
		
		if (joueurss != null)
		{
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
		}
		solo.setSize(100, 100);
		multi.setSize(100, 100);
		this.add(scrollPane, BorderLayout.WEST);
		this.add(scrollPane2, BorderLayout.EAST);
			
	}
	
	public void clic()
	{
		this.addTableaux();
	}
	
}
