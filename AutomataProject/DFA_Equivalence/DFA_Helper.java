
package DFA_Equivalence;

public class DFA_Helper {

    public static void displayDFA(DFA dfa) {
        for (State state : dfa.getStates()) {
            // Print state ID
            System.out.println("State ID: q" + state.getId());

            boolean hasTransitions = false;

            // Print transitions
            for (Character symbol : state.getTransitionSymbols()) {
                State nextState = state.getNextState(symbol);
                if (nextState != null) {
                    System.out.println("  On symbol '" + symbol + "', goes to State ID: q" + nextState.getId());
                    hasTransitions = true;
                } else {
                    System.out.println("  No transition on symbol '" + symbol + "'");
                }
            }

            if (!hasTransitions) {
                System.out.println("  This state has no transitions.");
            }

            // Check if it's a start state
            if (state.equals(dfa.getStartState())) {
                System.out.println("  ** This is a START state. **");
            }

            // Check if it's a final state
            if (state.isFinal()) {
                System.out.println("  ** This is a FINAL state. **");
            }
        }
    }//end method



    // Method to set the start state of a DFA based on state ID
    public static void setStartState(DFA dfa, int startStateId) {
        State startState = dfa.getState(startStateId);
        if (startState != null) {
            dfa.setStartState(startState);
        } else {
            System.out.println("Start state with ID " + startStateId + " does not exist in the DFA.");
        }
    }//end method

    // Method to set a state as a final state in a DFA based on state ID
    public static void setFinalState(DFA dfa, int finalStateId) {
        State finalState = dfa.getState(finalStateId);
        if (finalState != null) {
            finalState.setFinal(true);
            dfa.addFinalState(finalState);
        } else {
            System.out.println("Final state with ID " + finalStateId + " does not exist in the DFA.");
        }
    }//end method

}//end class

