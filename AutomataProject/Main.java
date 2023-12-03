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
			// String filePath1 = "AutomataProject/Frontend_Helpers/dfa1.txt";
			// String filePath2 = "AutomataProject/Frontend_Helpers/dfa2.txt";
			// DFA dfa1 = TEXT_Helper.readDFAFromFile(filePath1);
			// DFA dfa2 = TEXT_Helper.readDFAFromFile(filePath2);

			// Using JSON_Helper
			String filePath1 = "AutomataProject/Frontend_Helpers/dfa1.json";
			String filePath2 = "AutomataProject/Frontend_Helpers/dfa2.json";

			// Read JSON content from files
			String jsonDFA1 = new String(Files.readAllBytes(Paths.get(filePath1)));
			String jsonDFA2 = new String(Files.readAllBytes(Paths.get(filePath2)));

			// Parse the JSON content to create DFA objects
			DFA dfa1 = JSON_Helper.parseJSON(jsonDFA1);
			DFA dfa2 = JSON_Helper.parseJSON(jsonDFA2);

			// Display each DFA
			System.out.println("DFA 1:");
			DFA_Helper.displayDFA(dfa1);
			System.out.println("\nDFA 2:");
			DFA_Helper.displayDFA(dfa2);

			// Check for equivalence
			Equivalence_Checker checker = new Equivalence_Checker();
			boolean areEquivalent = checker.areEquivalent(dfa1, dfa2);
			System.out.println("\nAre the two DFAs equivalent? " + areEquivalent);

			// Print state ID matching if they are equivalent
			if (areEquivalent) {
				checker.printStateIdMatching(dfa1, dfa2);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
