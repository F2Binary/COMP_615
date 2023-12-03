package DFA_Equivalence;

public class Equivalence_Checker {

    public boolean areEquivalent(DFA dfa1, DFA dfa2) {
        // Check if both DFAs have the same number of states
        if (dfa1.getStates().size() != dfa2.getStates().size()) {
            return false;
        }

        // Iterate through each state in DFA 1
        for (State state1 : dfa1.getStates()) {
            // Get the corresponding state in DFA 2
            State state2 = getCorrespondingState(state1, dfa2);

            // If no corresponding state exists or states are not equivalent, DFAs are not equivalent
            if (state2 == null || !areStatesEquivalent(state1, state2)) {
                return false;
            }
        }

        return true;
    }//end method

    private boolean areStatesEquivalent(State state1, State state2) {
        // Check if both states are either final or non-final
        if (state1.isFinal() != state2.isFinal()) {
            return false;
        }

        // Compare transitions of both states
        for (Character symbol : state1.getTransitionSymbols()) {
            State nextState1 = state1.getNextState(symbol);
            State nextState2 = state2.getNextState(symbol);

            // Check if transitions on the same symbol lead to corresponding states
            if (!nextState1.correspondsTo(nextState2)) {
                return false;
            }
        }

        return true;
    }//end method

    // Function to print one-to-one state ID relationships between two DFAs
    public void printStateIdMatching(DFA dfa1, DFA dfa2) {
        System.out.println("State ID Matching between DFA1 and DFA2:");
        for (State state1 : dfa1.getStates()) {
            State state2 = getCorrespondingState(state1, dfa2);
            if (state2 != null) {
                System.out.println("DFA1 State ID: " + state1.getId() + " corresponds to DFA2 State ID: " + state2.getId());
            } else {
                System.out.println("DFA1 State ID: " + state1.getId() + " has no corresponding state in DFA2.");
            }
        }
    }

    private State getCorrespondingState(State state, DFA dfa2) {
        return dfa2.getState(state.getId());
    }//end method

}
