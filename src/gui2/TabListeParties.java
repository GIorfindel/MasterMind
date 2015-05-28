package gui2;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class TabListeParties extends AbstractTableModel {

    private final String[] colonnes = {"Nom de la partie", "Difficulté", "Pions max", "Coups max", "Couleurs max", "Couleurs multiples"};
    
    private ArrayList parties;
    
    public TabListeParties(ArrayList parties) {
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
		Object[] partie = (Object[]) this.parties.get(ligne);
		return partie[colonne];
	}
	
	public String getColumnName(int i) {
		return colonnes[i];
	}

}
