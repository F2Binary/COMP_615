package Frontend_Helpers;

import DFA_Equivalence.DFA;
import DFA_Equivalence.State;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TEXT_Helper {

    public static DFA readDFAFromFile(String filePath) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                contentBuilder.append(currentLine).append("\n");
            }
        }

        String fileContent = contentBuilder.toString();
        return parseDFA(fileContent);
    }

    private static DFA parseDFA(String dfaString) {
        DFA dfa = new DFA();
        Map<Integer, Map<Character, Integer>> transitionMap = new HashMap<>();

        // Regular expression to extract transitions
        Pattern pattern = Pattern.compile("\"(\\d+)\":\\s*\\{\\s*\"(\\d+)\":\\s*\\[\\s*\"([a-zA-Z])\"\\s*\\]\\s*\\}");
        Matcher matcher = pattern.matcher(dfaString);

        while (matcher.find()) {
            int fromStateId = Integer.parseInt(matcher.group(1));
            int toStateId = Integer.parseInt(matcher.group(2));
            char transitionSymbol = matcher.group(3).charAt(0);

            transitionMap.putIfAbsent(fromStateId, new HashMap<>());
            transitionMap.get(fromStateId).put(transitionSymbol, toStateId);
        }

        // Create DFA states and transitions based on extracted data
        for (Map.Entry<Integer, Map<Character, Integer>> entry : transitionMap.entrySet()) {
            int fromStateId = entry.getKey();
            State fromState = dfa.getState(fromStateId);
            for (Map.Entry<Character, Integer> trans : entry.getValue().entrySet()) {
                char symbol = trans.getKey();
                int toStateId = trans.getValue();
                State toState = dfa.getState(toStateId);
                fromState.addTransition(symbol, toState);
            }
        }


        return dfa;
    }
}
