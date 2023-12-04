package DFA_Equivalence;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class State {
    private int id;
    private boolean isFinal;
    private boolean isStart;
    private Map<Character, State> transitions;


    public State(int id) {
        this.id = id;
        this.isFinal = false;
        this.isStart = false;
        this.transitions = new HashMap<>();
    }

    // Add a transition from this state to another state
    public void addTransition(char symbol, State nextState) {
        transitions.put(symbol, nextState);
    }

    // Get the next state for a given transition symbol
    public State getNextState(char symbol) {
        return transitions.get(symbol);
    }

    // Get the set of transition symbols
    public Set<Character> getTransitionSymbols() {
        return transitions.keySet();
    }

    // Set the state as start
    public void setStart(boolean isStart) {
        this.isStart = isStart;
    }

    // Check if the state is a start state
    public boolean isStart() {
        return isStart;
    }

    // Check if the state is final
    public boolean isFinal() {
        return isFinal;
    }

    // Set the state as final
    public void setFinal(boolean isFinal) {
        this.isFinal = isFinal;
    }


    // Method to check if this state is the start state of a given DFA
    public boolean isStartState(DFA dfa) {
        return this.equals(dfa.getStartState());
    }

    // Get the state ID
    public int getId() {
        return id;
    }

    // Method to print state information
    public void printStateInfo() {
        System.out.println("\n** State ID: q" + id);

        // Handle transitions
        if (transitions.isEmpty()) {
            System.out.println("  No transitions from this state.");
        } else {
            for (Map.Entry<Character, State> entry : transitions.entrySet()) {
                System.out.println("  On symbol '" + entry.getKey() + "', goes to State ID: q" + entry.getValue().getId());
            }
        }

        // Print if this is a start state
        if (isStart) {
            System.out.println("  ** This is a START state. **");
        }

        // Print if this is a final state
        if (isFinal) {
            System.out.println("  ** This is a FINAL state. **");
        }
    }//end method


}//end class
