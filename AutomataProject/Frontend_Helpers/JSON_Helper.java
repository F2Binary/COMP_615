package Frontend_Helpers;

import org.json.JSONObject;
import org.json.JSONArray;
import DFA_Equivalence.DFA;
import DFA_Equivalence.State;

public class JSON_Helper {

    public static DFA parseJSON(String jsonString) {
        JSONObject jsonObject = new JSONObject(jsonString);
        DFA dfa = new DFA();

        // Iterate through each state in the JSON object
        for (String fromStateId : jsonObject.keySet()) {
            JSONObject transitions = jsonObject.getJSONObject(fromStateId);
            State fromState = dfa.getState(Integer.parseInt(fromStateId));

            // Iterate through each transition in the state
            for (String toStateId : transitions.keySet()) {
                JSONArray inputs = transitions.getJSONArray(toStateId);
                State toState = dfa.getState(Integer.parseInt(toStateId));

                // Add transitions for each input symbol
                for (int i = 0; i < inputs.length(); i++) {
                    char input = inputs.getString(i).charAt(0);
                    fromState.addTransition(input, toState);
                }
            }
        }

        return dfa;
    }
}
