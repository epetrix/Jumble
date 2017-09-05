import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Project5 {
	public static void main(String[] args) throws Exception {

		//RUN : java project 5 dictionary.txt jumbles.txt

		BufferedReader infileJ = new BufferedReader(new FileReader(args[1])); //read in jumbled
		BufferedReader infileD = new BufferedReader(new FileReader(args[0])); //read in dictionary
		HashMap<Integer, ArrayList<String>> map = new HashMap<Integer, ArrayList<String>>();	   //map for value and word
		HashMap<String, ArrayList<String>> mapDone = new HashMap<String, ArrayList<String>>(); //map for jumbled word and dictionary word(s) 
		ArrayList<String> jumbled = new ArrayList<String>();								   //Array list for jumbled words

		String word;			//infile.read in word
		int val = 0;			//key
		int found = 0;			//for iterating through chars
		boolean done = false;	//found word
		char [] char1;			//
		char [] char2; 			//

		//DICTIONARY
		while ((word = infileD.readLine()) != null){
			val = 0;
			for (int i = 0; i < word.length(); i ++) {			//assign each letter a value
				val = (int)word.charAt(i) + val;				//val = total for word
			}
			
			ArrayList<String> list = map.get(val);				//create an array list to add dictionary words to				

			if (list == null) {									//if empty, create and add word
				list = new ArrayList<String>();
				list.add(word);
			}

			else {
				list.add(word);									//add 
			}

			map.put(val, list);									//place array list of dictionary at the calculated val in map
			
		}//close while
	
		
		//JUMBLED
		while ((word = infileJ.readLine()) != null) {

			val = 0;
			for (int i = 0; i < word.length(); i++) {			//assign each letter a value
				val = (int)word.charAt(i) + val;				//val = total for word
			}

			ArrayList<String> list2 = map.get(val);						//create array list to add jumbled words to
			jumbled.add(word);
			Collections.sort(jumbled.subList(0, jumbled.size()));		//sort so that it's alphabetical

			ArrayList<String> foundIt = new ArrayList<String>();		//places matching jumbled to dictionary words together

			for (String temp : list2) {
					if (temp.length() != word.length()) {			//if words aren't same length
					break;											//move on to next word				
				}	
			
				else {
					char1 = temp.toCharArray();						//turn to char array and sort so alphabetical					
					char2 = word.toCharArray();

					Arrays.sort(char1);
					Arrays.sort(char2);

					if (Arrays.equals(char1, char2)) {				//check to see if they are the same, if so then add to foundIt array
						foundIt.add(temp);
						done = true;
					}
				}
			}

			if (done) {
				mapDone.put(word, foundIt);							//if done is true, place jumbled word with the dictionary matches array
			}
		}

		for (String doneWord : jumbled) {							//print out jumbled word and corresponding dictionary word(s) 
			System.out.print(doneWord);
			for (String doneWord2 : mapDone.get(doneWord))
			System.out.print(" " + doneWord2);
			System.out.println(); 
		}

	}//end main
}//end Project5
	