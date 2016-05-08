/*
 * Kem Andrew
 * CMPS 4013
 * Dr. Stringfellow
 * Coin Sorter program
 * 
 * This program randomly generates a number of coins and stores it in a 
 * text file. The coins generated are sorted and the final amount and 
 * number of each coins are displayed. Enjoy! 
 */

public class CoinSorterMain {

	public static void main(String[] args)  
	{
		//Create a new instance of CoinSorter class
		CoinSorter x = new CoinSorter();
		
		//x.randGen();
		x.welomeMessage();
		x.readFile();
		x.closeFile();
		x.exitMessage();
		
		//User choice menu
		//x.choiceMenu();
	}//End of main
	

}
