import java.util.*;
import java.io.*;
import java.math.BigInteger;

/**
* Intermediate Java (0401) Project 5 (2017)
* Reads in a text file of scrambled words and a dictionary file and unscrambles
* the words by calculating the int value of each word to find matches
* 
* Usage: java Jumble dictionary.txt scrambled.txt
*
* @author Eden Petri
*/

public class Jumble 
{
	private static BufferedReader infileD; 
	private static BufferedReader infileJ;

	private static HashMap<Integer, ArrayList<String>> dictionaryMap;  
	private static HashMap<String, ArrayList<String>> result; 
	private static ArrayList<String> jumbled; 

	private static String word; 
	private static int val;
	private static int found;
	private static boolean done; 
	private static char[] scrambledWord; 
	private static char[] dictionaryWord;  

	public static void main (String[] args) throws Exception 
	{
		infileD = new BufferedReader(new FileReader(args[0]));	//for reading dictionary.txt
		infileJ = new BufferedReader(new FileReader(args[1])); 	//for reading jumbles.txt

		dictionaryMap = new HashMap<Integer, ArrayList<String>>();		//stores value and word
		result = new HashMap<String, ArrayList<String>>(); 	//stores jumbled word and dictionary word
		jumbled = new ArrayList<String>();

		parseDictionary();
		parseScrambled(); 
		printResults(); 						
	}

	/**
	* Reads each word in the dictionary file and assings it an int value
	* which is used to store the word(s) in the dictionary map
	*/
	private static void parseDictionary() throws Exception  
	{
		while ((word = infileD.readLine()) != null)
		{
			val = 0;
			
			for (int i = 0; i < word.length(); i++) 
			{			
				val = (int)word.charAt(i) + val;				
			}
			
			ArrayList<String> dictionaryWords = dictionaryMap.get(val);				

			if (dictionaryWords == null) 
			{
				dictionaryWords = new ArrayList<String>();
				dictionaryWords.add(word);
			}

			else 
			{
				dictionaryWords.add(word);
			}

			dictionaryMap.put(val, dictionaryWords);
		}
	}


	/**
	* Reads each word in the scrambled file and assigns it an int value
	* it alphabetizes the words in a list   
	*/
	private static void parseScrambled() throws Exception 
	{
		while ((word = infileJ.readLine()) != null) 
		{
			val = 0;
			
			for (int i = 0; i < word.length(); i++) 
			{			
				val = (int)word.charAt(i) + val;
			}

			jumbled.add(word);
			ArrayList<String> scrambledWords = dictionaryMap.get(val);	

			//Collections.sort(jumbled.subList(0, jumbled.size()));

			ArrayList<String> foundIt = new ArrayList<String>();

			for (String temp : scrambledWords) 
			{
				if (temp.length() != word.length()) 
					break;				
			
				else 
				{
					scrambledWord = temp.toCharArray();					
					dictionaryWord = word.toCharArray();

					Arrays.sort(scrambledWord);
					Arrays.sort(dictionaryWord);

					if (Arrays.equals(scrambledWord, dictionaryWord)) 
					{
						foundIt.add(temp);
						done = true;
					}
				}
			}

			if (done) 
			result.put(word, foundIt);
		}

		Collections.sort(jumbled.subList(0, jumbled.size()));
	}

	/**
	* Prints the results in the format
	* scrambledWord     dictionaryWord(s),
	*/
	private static void printResults()
	{
		for (String word : jumbled) 
		{
			System.out.print("\n" + word + "\t\t");
			for (String matchingWord : result.get(word))
				System.out.print(" " + matchingWord + ", "); 
		}
	}
}