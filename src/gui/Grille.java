import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class Grille{
	
	public int[] genereCode(int dificult){
		int[] tab = new int[4];
		Random r = new Random();
		for(int i=0;i<tab.length;i++)
			tab[i]= r.nextInt(2*(dificult+1))+1;
		return tab;		
	}
	


	public boolean[] caseN(int[] code, int[] test){
		boolean[] caseNoire = new boolean[code.length];		
		for(int i=0;i<code.length;i++){
			caseNoire[i]=false;
			if(code[i]==test[i]) caseNoire[i]=true;
		}
		return caseNoire;
	}
	
	public boolean[] caseB(int[] code, int[] test){
		boolean[] piontN = caseN(code,test);
		int taille = compte(caseN(code,test));
		boolean[] piontB=null;
		if(taille!=4){
			piontB= new boolean[4-taille];
			int[] b = new int[4-taille];
			int[] b2 = new int[4-taille];
			int j=0;
			
			for(int i=0;i<code.length;i++)
				if(!piontN[i]){
					b[j]=code[i];
					b2[j]=test[i];
					j++;
				}
			boolean continuer;
			j=0;
			for(int i=0;i<b.length;i++){
				j=0;
				continuer=true;
				while((continuer==true)&&(j<b2.length)){
					if((!piontB[j])&&(b[i]==b2[j])){
						piontB[j]=true;
						continuer=false;						
					}
					j++;
				}				
			}
		}
		return piontB;			
			
	}
	
	public int compte(boolean[] t){
		int res=0;
		if(t!=null)
			for(int i=0;i<t.length;i++)
				if(t[i]==true) res++;
		return res;		
	}
	
	public boolean lineFull(int[] test){
		for(int i=0;i<test.length;i++){
			if(test[i]==0) return false;
		}
		return true;		
	}
	
	
	
	
}
