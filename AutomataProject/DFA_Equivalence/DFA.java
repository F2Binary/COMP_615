package DFA_Equivalence;

import java.util.Map;
import java.util.HashMap;
import java.util.Collection;

public class DFA {
    private Map<Integer, State> states;

    public DFA() {
        this.states = new HashMap<>();
    }

    public State getState(int id) {
        states.putIfAbsent(id, new State(id));
        return states.get(id);
    }

    public Collection<State> getStates() {
        return states.values();
    }

    // Set a state as a final state
    public void setFinalState(int id, boolean isFinal) {
        State state = getState(id);
        state.setFinal(isFinal);
    }

}
