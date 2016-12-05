package DFA;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

import java.util.*;


public class JsonParser {
    public ArrayList<JSONObject> parse(String json) throws ParseException {
        ArrayList<JSONObject> allObjects = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonObjects = (JSONArray) jsonParser.parse(json);
        for (Object jsonObject: jsonObjects) allObjects.add((JSONObject) jsonObject);
        return allObjects;
    }

    public HashMap<String, HashSet<String>> fiveTuple(JSONObject jsonTuple){
        HashMap<String, HashSet<String>> fiveTuple = new HashMap<>();
        JSONArray start_state = new JSONArray();
        start_state.add(jsonTuple.get("start-state"));
        HashSet states = toHashSet((JSONArray) jsonTuple.get("states"));
        HashSet final_states = toHashSet((JSONArray) jsonTuple.get("final-states"));
        HashSet alphabets = toHashSet((JSONArray) jsonTuple.get("alphabets"));
        fiveTuple.put("finalStates", final_states);
        fiveTuple.put("setOfStates", states);
        fiveTuple.put("initialStates",toHashSet(start_state));
        fiveTuple.put("setOfAlphabets", alphabets);
        return fiveTuple;
    }

    private HashSet<String> toHashSet(JSONArray array){
        HashSet<String> hashset = new HashSet<>();
        for (Object string : array) hashset.add((String) string);
        return hashset;
    }

    public TransitionMaker transitionMaker(JSONObject jsonTuple){
        HashSet<String[]> transitions = new HashSet<>();
        JSONObject transitionMaker =(JSONObject) jsonTuple.get("delta");
        for (Object key: transitionMaker.keySet()) {
            JSONObject value = (JSONObject) transitionMaker.get(key);
            for (Object inner_key: value.keySet()) {
                String[] strings = {String.format("%s,%s",key,inner_key), (String) value.get(inner_key)};
                transitions.add(strings);
            }
        }
        return TransitionMaker.createTransition(transitions);
    };
}

