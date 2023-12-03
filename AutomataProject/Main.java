import Frontend_Helpers.TEXT_Helper;
import DFA_Equivalence.DFA;
import DFA_Equivalence.DFA_Helper;
import DFA_Equivalence.Equivalence_Checker;
import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		System.out.println("Testing DFA1 and DFA2 Equivalence Checker.");

		try {
			String filePath1 = "AutomataProject/Frontend_Helpers/dfa1.txt";
			String filePath2 = "AutomataProject/Frontend_Helpers/dfa2.txt";
			DFA dfa1 = TEXT_Helper.readDFAFromFile(filePath1);
			DFA dfa2 = TEXT_Helper.readDFAFromFile(filePath2);

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
