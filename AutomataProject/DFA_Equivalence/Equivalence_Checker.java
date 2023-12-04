package DFA_Equivalence;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
//import org.apache.commons.lang3.tuple.Pair;
import javafx.util.Pair;


public class Equivalence_Checker {

    private Set<Pair<State, State>> visited;

    public boolean areEquivalent(DFA dfa1, DFA dfa2) {
        visited = new HashSet<>();
        return areStatesEquivalent(dfa1.getStartState(), dfa2.getStartState());
    }

    private boolean areStatesEquivalent(State state1, State state2) {

        // Debugging
        state1.printStateInfo();
        state2.printStateInfo();

        // Base cases
        if (state1 == null || state2 == null) return state1 == state2;
        if (state1.isFinal() != state2.isFinal()) return false;
        Pair<State, State> statePair = new Pair<>(state1, state2);
        if (visited.contains(statePair)) return true; // Already visited this pair

        visited.add(statePair);

        // Compare transitions
        for (Character symbol : getAllSymbols(state1, state2)) {
            State nextState1 = state1.getNextState(symbol);
            State nextState2 = state2.getNextState(symbol);

            // Print transition comparison details
            System.out.println("Comparing transitions: q" + state1.getId() + " --[" + symbol + "]--> q" + (nextState1 != null ? nextState1.getId() : "null") +
                    " with q" + state2.getId() + " --[" + symbol + "]--> q" + (nextState2 != null ? nextState2.getId() : "null"));

            if (!areStatesEquivalent(nextState1, nextState2)) return false;
        }
        return true;
    }

    private Set<Character> getAllSymbols(State state1, State state2) {
//      // Debugging
//      System.out.println("\nState A: " + state1.getTransitionSymbols() + "\nState B: " + state2.getTransitionSymbols());

        // Combine symbols from both states
        Set<Character> symbols = new HashSet<>(state1.getTransitionSymbols());
        symbols.addAll(state2.getTransitionSymbols());
        return symbols;
    }


    // Method to generate an adjacency list for a given DFA
//    private Map<Integer, List<String>> generateAdjacencyList(DFA dfa) {
//        Map<Integer, List<String>> adjacencyList = new HashMap<>();
//        for (State state : dfa.getStates()) {
//            List<String> transitions = new ArrayList<>();
//            for (Character symbol : state.getTransitionSymbols()) {
//                State nextState = state.getNextState(symbol);
//                if (nextState != null) {
//                    transitions.add(symbol + "->q" + nextState.getId());
//                }
//            }
//            adjacencyList.put(state.getId(), transitions);
//        }
//        return adjacencyList;
//    }
//
//    // Method to print an adjacency list
//    private void printAdjacencyList(Map<Integer, List<String>> adjacencyList, String dfaName) {
//        System.out.println("\nAdjacency List for " + dfaName + ":");
//        for (Map.Entry<Integer, List<String>> entry : adjacencyList.entrySet()) {
//            System.out.println("State q" + entry.getKey() + ": " + entry.getValue());
//        }
//    }
//
//    // Method to compare adjacency lists of two DFAs
//    private boolean compareAdjacencyLists(Map<Integer, List<String>> list1, Map<Integer, List<String>> list2) {
//        // Assuming the first state read is the start state (with the lowest ID)
//        int startState1 = list1.keySet().stream().min(Integer::compare).orElse(-1);
//        int startState2 = list2.keySet().stream().min(Integer::compare).orElse(-1);
//
//        return startState1 != -1 && startState2 != -1 && list1.get(startState1).equals(list2.get(startState2));
//    }

}
