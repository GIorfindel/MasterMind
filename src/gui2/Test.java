package gui2;

public class Test {

	/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		if (args.length == 0)
		{
			Fenetre f= new Fenetre();
		}
		else if (args[0].equals("--help"))
		{
			System.out.println("Pour lancer le mastermind, tapez la commande suivante : ./client.sh ou ./client.bat");
			System.out.println("Report bugs to florian.choiselle@free.fr");
		}
		else if (args[0].equals("--version"))
		{
			System.out.println("Mastermind 1\nCopyright (C) 1996 Free Software Foundation, Inc.\nMastermind comes with NO WARRANTY, to the extent permitted by law.\nYou may redistribute copies of Mastermind under the terms of the GNU General Public License.\nFor more information about these matters, see the files named COPYING.");
		}
	}

}
