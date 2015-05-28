package gui2;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class TabListeParties extends AbstractTableModel {

    private final String[] colonnes = {"Nom de la partie", "Difficulté", "Pions max", "Coups max", "Couleurs max", "Couleurs multiples"};
    
    private ArrayList<Object[]> parties;
    
    public TabListeParties(ArrayList<Object[]> parties) {
    	this.parties = parties;
    }
    
	@Override
	public int getColumnCount() {
		return 6;
	}

	@Override
	public int getRowCount() {
		return parties.size();
	}

	@Override
	public Object getValueAt(int ligne, int colonne) {
		Object[] partie = this.parties.get(ligne);
		return partie[colonne];
	}
	
	public String getColumnName(int i) {
		return colonnes[i];
	}

}
