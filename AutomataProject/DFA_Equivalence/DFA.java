package DFA_Equivalence;

import java.util.Map;
import java.util.HashMap;

public class DFA {
    private Map<Integer, State> states;

    public DFA() {
        this.states = new HashMap<>();
    }

    public State getState(int id) {
        states.putIfAbsent(id, new State(id));
        return states.get(id);
    }

}
