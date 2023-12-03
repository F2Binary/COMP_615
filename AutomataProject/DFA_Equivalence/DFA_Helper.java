
package DFA_Equivalence;

public class DFA_Helper {

    public static void displayDFA(DFA dfa) {

        for (State state : dfa.getStates()) {
            System.out.println("State ID: " + state.getId());
            for (Character symbol : state.getTransitionSymbols()) {
                State nextState = state.getNextState(symbol);
                System.out.println("  On symbol '" + symbol + "' goes to State ID: " + nextState.getId());
            }
            if (state.isFinal()) {
                System.out.println("  This is a final state.");
            }
        }
    }
}
