package serveur;


public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length == 0)
		{
			Serveur s = new Serveur( 15000 );
			s.start();
		}
		
		else if (args[0].equals("--help"))
		{
			System.out.println("Pour lancer le serveur, tapez la commande suivante : ./serveur.sh ou ./serveur.bat");
			System.out.println("Pour arrêter le serveur, tapez \"stop\" après l'avoir lancé");
			System.out.println("Report bugs to florian.choiselle@free.fr");
		}
		
		else if (args[0].equals("--version"))
		{
			System.out.println("Mastermind 1\nCopyright (C) 1996 Free Software Foundation, Inc.\nMastermind comes with NO WARRANTY, to the extent permitted by law.\nYou may redistribute copies of Mastermind under the terms of the GNU General Public License.\nFor more information about these matters, see the files named COPYING.");
		}
	}

}
