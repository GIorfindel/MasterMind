package gui2;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FiltreImage extends FileFilter{
	
	private String[] imageAccepter;
	
	public FiltreImage(){
		this.imageAccepter = new String[4];
		this.imageAccepter[0] = "png";
		this.imageAccepter[1] = "jpeg";
		this.imageAccepter[2] = "jpg";
		this.imageAccepter[3] = "bmp";
	}
	
	private boolean appartient( String extension ){//Exemple png, jpg
		for( int i = 0; i < this.imageAccepter.length; i++ ){
			if( extension.equals( this.imageAccepter[i] ) ){
				return true;
			}
		}
		return false;
	}
	
	public boolean accept( File f ){
		if( f.isDirectory() ){
			return true;
		}
		String nomF = f.getName();
		String extension = null;
		int i = nomF.lastIndexOf('.');
		if( i > 0 && i < nomF.length() - 1 ){
			extension = nomF.substring( i + 1 ).toLowerCase();
		}
		return extension != null && this.appartient( extension );
	}
	
	public String getDescription(){
		return "Les images png, jpeg, jpg, bmp";
	}
}
