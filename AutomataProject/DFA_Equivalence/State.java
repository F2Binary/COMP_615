package DFA_Equivalence;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class State {
    private int id;
    private boolean isFinal;
    private Map<Character, State> transitions;


    public State(int id) {
        this.id = id;
        this.isFinal = false;
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

    // Check if the state is final
    public boolean isFinal() {
        return isFinal;
    }

    // Set the state as final
    public void setFinal(boolean isFinal) {
        this.isFinal = isFinal;
    }

    // Get the state ID
    public int getId() {
        return id;
    }

}//end class
