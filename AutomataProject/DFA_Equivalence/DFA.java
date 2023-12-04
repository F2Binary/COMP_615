package DFA_Equivalence;

import java.util.HashSet;
import java.util.Set;

public class DFA {
    private Set<State> states;
    private State startState;
    private Set<State> finalStates;

    public DFA() {
        this.states = new HashSet<>();
        this.finalStates = new HashSet<>();
    }

    public void setStartState(State startState) {
        this.startState = startState;
    }
    public State getStartState() {
        return startState;
    }

    public void addFinalState(State finalState) {
        finalStates.add(finalState);
    }

    public Set<State> getFinalStates() {
        return finalStates;
    }

    public State getState(int id) {
        // Iterate over the set to find the state with the given ID
        for (State state : states) {
            if (state.getId() == id) {
                return state;
            }
        }
        return null; // or throw an exception if a state is not found
    }

    public void addState(State state) {
        states.add(state);
    }

    public Set<State> getStates() {
        return states;
    }

}//end class
