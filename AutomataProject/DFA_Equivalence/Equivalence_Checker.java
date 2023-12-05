package DFA_Equivalence;

import java.util.*;
//import org.apache.commons.lang3.tuple.Pair;
import javafx.util.Pair;


public class Equivalence_Checker {

    private Set<Pair<State, State>> visited;


    public boolean areEquivalent(DFA dfa1, DFA dfa2) {
        HashSet<Integer> visitedDFA1 = new HashSet<>();
        HashSet<Integer> visitedDFA2 = new HashSet<>();
        return areStatesEquivalent(dfa1.getStartState(), dfa2.getStartState(), visitedDFA1, visitedDFA2);
    }


    private boolean areStatesEquivalent(State startState1, State startState2, HashSet<Integer> visitedDFA1, HashSet<Integer> visitedDFA2) {
        Queue<Pair<State, State>> queue = new LinkedList<>();
        Map<Pair<Integer, Integer>, Boolean> stateAssociations = new HashMap<>();
//        Map<Pair<Integer, Integer>, List<Character>> stateAssociationsWithTransitions = new HashMap<>();

        queue.add(new Pair<>(startState1, startState2));

        while (!queue.isEmpty()) {
            Pair<State, State> currentPair = queue.poll();
            State state1 = currentPair.getKey();
            State state2 = currentPair.getValue();

            // Debugging
            if (state1 != null) state1.printStateInfo();
            if (state2 != null) state2.printStateInfo();

            // Base cases
            if (state1 == null || state2 == null) {
                if (state1 != state2) return false;
                continue;
            }
            if (state1.isFinal() != state2.isFinal()) return false;
            if (visitedDFA1.contains(state1.getId()) && visitedDFA2.contains(state2.getId())) continue; // Already visited these states

            visitedDFA1.add(state1.getId());
            visitedDFA2.add(state2.getId());

            // Compare transitions
            for (Character symbol : getAllSymbols(state1, state2)) {
                State nextState1 = state1.getNextState(symbol);
                State nextState2 = state2.getNextState(symbol);

                // Print transition comparison details
                System.out.println("DFA1: Comparing transitions: q" + state1.getId() + " --[" + symbol + "]--> q" + (nextState1 != null ? nextState1.getId() : "null") +
                        " with DFA2: q" + state2.getId() + " --[" + symbol + "]--> q" + (nextState2 != null ? nextState2.getId() : "null"));


//                Pair<Integer, Integer> nextStatePair = new Pair<>(nextState1.getId(), nextState2.getId());
//                if (nextState1 != null && nextState2 != null) {
//                    // Check if the DFA1 state is already associated with a different DFA2 state
//                    for (Map.Entry<Pair<Integer, Integer>, Boolean> entry : stateAssociations.entrySet()) {
//                        if (entry.getKey().getKey().equals(nextState1.getId()) && !entry.getKey().getValue().equals(nextState2.getId())) {
//                            // Conflicting association found
//                            printStateDiscrepancy(state1, state2, stateAssociations, nextState1, nextState2, symbol);
//                            return false;
//                        }
//                    }
//
//                    stateAssociations.put(nextStatePair, true);
//
//                    if (!visitedDFA1.contains(nextState1.getId()) && !visitedDFA2.contains(nextState2.getId())) {
//                        queue.add(new Pair<>(nextState1, nextState2));
//                    }
//                }

                Pair<Integer, Integer> nextStatePair = new Pair<>(nextState1.getId(), nextState2.getId());
                if (nextState1 != null && nextState2 != null) {
                    // Check for conflicting associations in both orders
                    boolean conflictingAssociation = stateAssociations.entrySet().stream()
                            .anyMatch(entry ->
                                    (entry.getKey().getKey().equals(nextState1.getId()) && !entry.getKey().getValue().equals(nextState2.getId())) ||
                                            (entry.getKey().getValue().equals(nextState1.getId()) && !entry.getKey().getKey().equals(nextState2.getId()))
                            );

                    if (conflictingAssociation) {
                        // Conflicting association found
                        printStateDiscrepancy(state1, state2, stateAssociations, nextState1, nextState2);
                        return false;
                    }

                    stateAssociations.put(nextStatePair, true);
//                    stateAssociations.computeIfAbsent(nextStatePair, k -> new ArrayList<>()).add(symbol);

                    if (!visitedDFA1.contains(nextState1.getId()) && !visitedDFA2.contains(nextState2.getId())) {
                        queue.add(new Pair<>(nextState1, nextState2));
                    }
                }

            }
        }
        printStateAssociations(stateAssociations);
        return true;

    }//end method

    private void printStateAssociations(Map<Pair<Integer, Integer>, Boolean> stateAssociations) {
        System.out.println("State Associations:");
        for (Map.Entry<Pair<Integer, Integer>, Boolean> entry : stateAssociations.entrySet()) {
            System.out.println("DFA1 State q" + entry.getKey().getKey() +
                    " is associated with DFA2 State q" + entry.getKey().getValue());
        }
    }//end method

//    private void printStateDiscrepancy(State state1, State state2, Map<Pair<Integer, Integer>, List<Character>> stateAssociationsWithTransitions, State conflictingState1, State conflictingState2, Character symbol) {
//        System.out.println("State discrepancy found:");
//        System.out.println("State1: " + (state1 != null ? "q" + state1.getId() : "null"));
//        System.out.println("State2: " + (state2 != null ? "q" + state2.getId() : "null"));
//        if (conflictingState1 != null && conflictingState2 != null && symbol != null) {
//            System.out.println("Conflict: Trying to associate DFA1 State q" + conflictingState1.getId() +
//                    " with DFA2 State q" + conflictingState2.getId() +
//                    " on symbol '" + symbol + "', which conflicts with existing associations.");
//
//            Pair<Integer, Integer> conflictingPair = new Pair<>(conflictingState1.getId(), conflictingState2.getId());
//            if (stateAssociationsWithTransitions.containsKey(conflictingPair)) {
//                System.out.println("Existing association for this pair involves symbols: " + stateAssociationsWithTransitions.get(conflictingPair));
//            } else {
//                System.out.println("No existing associations found for this pair.");
//            }
//        }
//        printStateAssociationsWithTransitions(stateAssociationsWithTransitions);
//    } //end method

    private void printStateDiscrepancy(State state1, State state2, Map<Pair<Integer, Integer>, Boolean> stateAssociations, State conflictingState1, State conflictingState2) {
        System.out.println("State discrepancy found:");
        System.out.println("State1: " + (state1 != null ? "q" + state1.getId() : "null"));
        System.out.println("State2: " + (state2 != null ? "q" + state2.getId() : "null"));
        if (conflictingState1 != null && conflictingState2 != null) {
            System.out.println("Conflict: Trying to associate DFA1 State q" + conflictingState1.getId() +
                    " with DFA2 State q" + conflictingState2.getId() + ", which conflicts with existing associations.");

            Pair<Integer, Integer> conflictingPair = new Pair<>(conflictingState1.getId(), conflictingState2.getId());
            if (stateAssociations.containsKey(conflictingPair)) {
                System.out.println("Existing association for this pair: " + stateAssociations.get(conflictingPair));
            } else {
                System.out.println("No existing associations found for this pair.");
            }
        }
        printStateAssociations(stateAssociations);
    } //end method

//    private boolean areStatesEquivalent(State state1, State state2, HashSet<Integer> visitedDFA1, HashSet<Integer> visitedDFA2) {
//
//        // Debugging
//        state1.printStateInfo();
//        state2.printStateInfo();
//
//
//        // Base cases
//        if (state1 == null || state2 == null) return state1 == state2;
//        if (state1.isFinal() != state2.isFinal()) return false;
//        if (visitedDFA1.contains(state1.getId()) && visitedDFA2.contains(state2.getId())) return true; // Already visited these states
//
//        visitedDFA1.add(state1.getId());
//        visitedDFA2.add(state2.getId());
//
//        // Compare transitions
//        for (Character symbol : getAllSymbols(state1, state2)) {
//            State nextState1 = state1.getNextState(symbol);
//            State nextState2 = state2.getNextState(symbol);
//
//            // Print transition comparison details
//            System.out.println("DFA1: Comparing transitions: q" + state1.getId() + " --[" + symbol + "]--> q" + (nextState1 != null ? nextState1.getId() : "null") +
//                    " with DFA2: q" + state2.getId() + " --[" + symbol + "]--> q" + (nextState2 != null ? nextState2.getId() : "null"));
//
//            if (!areStatesEquivalent(nextState1, nextState2, visitedDFA1, visitedDFA2)) return false;
//        }
//        return true;
//    }

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
