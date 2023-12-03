package DFA_Equivalence;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class State {
    private int id;
    private boolean isFinal;
    private Map<Character, State> transitions;

    public State(int id) {
        this.id = id;
        this.transitions = new HashMap<>();
        this.isFinal = false;
    }

    // Method to add a transition
    public void addTransition(char input, State toState) {
        transitions.put(input, toState);
    }

    // Getters and other methods
    public int getId() {
        return id;
    }

    public State getNextState(char input) {
        return transitions.get(input);
    }

    public Set<Character> getTransitionSymbols() {
        return transitions.keySet();
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean isFinal) {
        this.isFinal = isFinal;
    }

    public boolean correspondsTo(State otherState) {
        return this.id == otherState.getId();
    }
}
