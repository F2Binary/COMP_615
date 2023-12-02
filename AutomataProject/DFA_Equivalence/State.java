package DFA_Equivalence;

import java.util.Map;
import java.util.HashMap;

public class State {
    private int id;
    private Map<Character, State> transitions;

    public State(int id) {
        this.id = id;
        this.transitions = new HashMap<>();
    }

    // Method to add a transition
    public void addTransition(char input, State toState) {
        transitions.put(input, toState);
    }


}
