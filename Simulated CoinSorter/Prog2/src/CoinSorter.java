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

import java.util.ArrayList;
import java.util.*;
import java.util.Random;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class CoinSorter {
	
	//The variables hold coin denominations
	public int pennies;
	public int nickels;
	public int dimes;
	public int quarters;
	
	//The variables hold coin rolls
	public final int pennieRoll = 50;
	public final int nickelRoll = 40;
	public final int dimeRoll = 50;
	public final int quartersRoll = 40;
	
	//These variables hold the coin values
	public final double pen = 0.01;
	public final double nick = 0.05;
	public final double dim = 0.1;
	public final double quart = 0.25;
	
	//Displays coin markers
	public int marker;
	
	//Scanner object
	private Scanner reading;
	private Scanner filename;
	
	//This variable stores the name of the file
	String coinFile;
	
	//This array holds values of coins to be sorted
	public int [] coinsArray;
	
	//This ArrayList stores values of coins read in
	ArrayList<Integer> coins = new ArrayList<>();
	
	//This array Store the random coins generated
	ArrayList<Integer> randCoins;
	
	//Default constructor
	CoinSorter()
	{
		pennies = 0;
		nickels = 0;
		dimes = 0;
		quarters = 0;
	}
	
	//Parameterized constructor
	CoinSorter(int pen, int nick, int dim, int quart)
	{
		this.pennies = pen;
		this.nickels = nick;
		this.dimes = dim;
		this.quarters = quart;
	}
	
	//Open the file
	public void openFile(String myfile)
	{
		coinFile = myfile;
		try 
		{
			reading = new Scanner(new File(coinFile));
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("Could not find file.");
			e.printStackTrace();
		}
	}//End of openFile
	
	//Reads the file
	public void readFile()
	{
		int check = -1;
		int coin;
		while(reading.hasNext()) //reading.nextInt()!= check)
		{
			//Convert string coin type to integer 
			coin = Integer.parseInt(reading.next());
			
			//This statement checks for the -1 marker to know when coins
			//need to be sorted
			if(coin == check)
			{
				//Increment display marker
				marker++;
				
				//Sort coins
				System.out.println("Sorting...");
				fillRolls();
				
				//clears the array list
				coins.clear();
			}
			
			//System.out.println(coin);
			coins.add(coin);  
		}
		fillRolls();
		System.out.println("Done reading the file.");
	}//End of readFile
	
	//Close the file
	public void closeFile()
	{
		reading.close();
		System.out.println("File closed!");
	}
	
	//Prints the contents of the ArrayList
	public void printNums()
	{
		for (int i = 0; i<coins.size(); i++)
		{
			System.out.println(coins.get(i));
		}
	}
	
	//Sorts the coins and makes calculations
	public void fillRolls()
	{
		//This loop copies the coins from the ArrayList to an array
		coinsArray = new int[coins.size()];
		for(int i = 0; i<coins.size(); i++)
		{
			coinsArray[i] = coins.get(i);
		}
		
		System.out.println(coins.size() + " coins");
		
		//Stores the different coins
		int coinSize;
		
		//This loop counts the number of each coins 
		for (int i=0; i < coinsArray.length; i++)
		{
			coinSize = coinsArray[i];
			//System.out.println(coinSize);
			switch (coinSize)
			{
				case 1:
					pennies++;
					break;
				case 5:
					nickels++;
					break;
				case 10:
					dimes++;
					break;
				case 25:
					quarters++;
					break;
				default:
					break;
			}//end of switch
		}//end of for loop
		System.out.println("Coins sorted successfully.");
		System.out.println();
		computeTotal();
	}//end of sortCoins
	
	//This class prints the final output
	public void computeTotal()
	{
		//This variable hold the total amount
		double total = ((pennies)*pen) + ((nickels)* nick) + ((dimes)* dim) + ((quarters)* quart);
		
		System.out.println("After " + marker + " marker");
		
		//Pennies
		System.out.println((pennies/pennieRoll) + " rolls of pennies + " 
						+ (pennies%pennieRoll) + " pennies = $" + String.format("%.3g", (pennies)*pen));
		
		//Nickels
		System.out.println((nickels/nickelRoll) + " rolls of nickels + " 
				+ (nickels%nickelRoll) + " nickels = $" + String.format("%.3g", (nickels)* nick));
		
		//Dimes
		System.out.println((dimes/dimeRoll) + " rolls of dimes + " 
				+ (dimes%dimeRoll) + " dimes = $" + String.format("%.3g", (dimes)* dim));
		
		//Quarters
		System.out.println((quarters/quartersRoll) + " rolls of quarters + " 
				+ (quarters%quartersRoll) + " quarters = $" + String.format("%.4g", (quarters)* quart));
		
		
		System.out.println("Total =                   $" + String.format("%.4g", total));
		
	}
	
	public void ranGenTwo()
	{
		try {
		    Path tempFile = Files.createTempFile(null, ".txt");
		    System.out.format("The temporary file" +
		        " has been created: %s%n", tempFile)
		;
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
	}
	
	//This method generates random coins
	public void randGen()
	{	
		System.out.println("Randomly generating coins...");
		//stores random coins
		randCoins = new ArrayList<>();
		
		//new random object
		Random rand = new Random();
		
		//stores the value of the random number
		int ranNum;
		
		//counter for the number of random numbers
		int counter = 0;
		
		//this while-loop gets numbers in range
		while (counter != 500) 
		{
			//Generate random numbers between 1 and 25
			ranNum = rand.nextInt(25)+1;
			
			//Save number only if it is 1,5,10 or 25
			if(ranNum == 1 || ranNum == 5|| ranNum == 10 || ranNum == 25)
			{
				//Add the random coin/number to the array list
				randCoins.add(ranNum);
				
				//System.out.println(ranNum);
				counter++;
			}
		}
		System.out.println(counter + " coins generated!");
		System.out.println("Saving to file...");
		
		//save random coins to a text file
		saveCoins(randCoins);
	}
	
	//This method saves the coins generated to a text file
	public void saveCoins(ArrayList<Integer> arr)
	{
		String filename = "out.txt";
		try 
		{
			FileWriter fw = new FileWriter(filename);
			PrintWriter pw = new PrintWriter(fw);
			
			for(int i = 0; i < arr.size(); i++)
			{
				//20 coins per line
				if((i%20) == 0)
				{
					pw.write('\n');
					//System.lineSeparator();
				}
				pw.write(randCoins.get(i).toString() + " ");
				//System.out.println(randCoins.get(i).toString() + " ");
			}
			pw.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("File saved!");
	}//end of saveCoins
	
	
	//This method displays the welcome screen
	public void welomeMessage() 
	{
		System.out.println("Welcome to the Coin Sorting program.");
		System.out.println("We're glad you're here!");
		System.out.println("To proceed please enter the file name. ");
		runCoinSorter();	
		
		
	}
	
	//This method accepts user input for file name
	public void runCoinSorter()
	{
		System.out.println("Enter 'out.txt': ");
		filename = new Scanner(System.in);
		coinFile = filename.nextLine();
		openFile(coinFile);
	}
	
	//This method displays contents of the file
	public void displayFile(String myfile) throws IOException
	{
		System.out.println("Displaying contents of file: ");
		BufferedReader in = new BufferedReader(new FileReader(myfile));
		String line;
		try {
			while((line = in.readLine()) != null)
			{
			    System.out.println(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		in.close();
	}
	
	//Choice menu
	public void choiceMenu()
	{	
		//Call to the menu
		choice();
		
		//User menu options
		int choice;
		
		Scanner choices = new Scanner(System.in);
		choice = choices.nextInt();
		
		//Let user choose from options
		while(choice != 4)
		{
			choice();
			switch (choice)
			{
				case 1:
					randGen();
					break;
				case 2:
				try {
					openFile(coinFile);
					displayFile(coinFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					break;
				case 3:
					runCoinSorter();
					break;
				case 4:
					exitMessage();
					break;
			}//end of switch
		}//End of while loop
		choices.close();
	}
	
	
	//choice header
	public void choice()
	{
		System.out.println("MENU: ");
		System.out.println("1. Generate random coins ");
		System.out.println("2. Display contents of file");
		System.out.println("3. Run the Coin Sorter");
		System.out.println("4. Exit");
	}
	
	//This method displays an exit message
	public void exitMessage()
	{
		System.out.println("Thank you for using this program. Hope you enjoyed it!");
	}
	
}
