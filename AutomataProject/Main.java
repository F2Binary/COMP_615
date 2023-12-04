import Frontend_Helpers.JSON_Helper;
import DFA_Equivalence.DFA;
import DFA_Equivalence.DFA_Helper;
import DFA_Equivalence.Equivalence_Checker;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
	public static void main(String[] args) {
		System.out.println("Testing DFA1 and DFA2 Equivalence Checker.");

		try {

			// Using TEXT_Helper
			// String filePath1 = "AutomataProject/Frontend_Helpers/Testing/txt_folder/dfa1.txt";
			// String filePath2 = "AutomataProject/Frontend_Helpers/Testing/txt_folder/dfa2.txt";
			// DFA dfa1 = TEXT_Helper.readDFAFromFile(filePath1);
			// DFA dfa2 = TEXT_Helper.readDFAFromFile(filePath2);

			// Using JSON_Helper
			String filePath1 = "AutomataProject/Frontend_Helpers/Testing/json_folder/dfa1.json";
			String filePath2 = "AutomataProject/Frontend_Helpers/Testing/json_folder/dfa2.json";
			String filePath3 = "AutomataProject/Frontend_Helpers/Testing/json_folder/dfa3.json";
			String filePath4 = "AutomataProject/Frontend_Helpers/Testing/json_folder/dfa4.json";
			String filePath5 = "AutomataProject/Frontend_Helpers/Testing/json_folder/dfa5.json";

			// Read JSON content from files
			String jsonDFA1 = new String(Files.readAllBytes(Paths.get(filePath1)));
			String jsonDFA2 = new String(Files.readAllBytes(Paths.get(filePath2)));
			String jsonDFA3 = new String(Files.readAllBytes(Paths.get(filePath3)));
			String jsonDFA4 = new String(Files.readAllBytes(Paths.get(filePath4)));
			String jsonDFA5 = new String(Files.readAllBytes(Paths.get(filePath5)));

			// Parse the JSON content to create DFA objects
			DFA dfa1 = JSON_Helper.parseJSON(jsonDFA1);
			DFA dfa2 = JSON_Helper.parseJSON(jsonDFA2);
			DFA dfa3 = JSON_Helper.parseJSON(jsonDFA3);
			DFA dfa4 = JSON_Helper.parseJSON(jsonDFA4);
			DFA dfa5 = JSON_Helper.parseJSON(jsonDFA5);

			// Assign DFA for testing here
			DFA dfaA = dfa3;
			DFA dfaB = dfa4;

			// Display each DFA
			System.out.println("DFA A:");
			DFA_Helper.displayDFA(dfaA);
			System.out.println("\nDFA B:");
			DFA_Helper.displayDFA(dfaB);

			// Check for equivalence
			Equivalence_Checker checker = new Equivalence_Checker();
			boolean areEquivalent = checker.areEquivalent(dfaA, dfaB);
			System.out.println("\nAre the two DFAs equivalent? " + areEquivalent);

			// Print state ID matching if they are equivalent
			if (areEquivalent) {
				checker.printStateIdMatching(dfaA, dfaB);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}//end main
}//end class

