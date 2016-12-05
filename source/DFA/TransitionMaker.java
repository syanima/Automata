package DFA;

import java.util.HashMap;
import java.util.HashSet;

public class TransitionMaker {
     HashMap<String,String> transitions;

    private TransitionMaker() {
        this.transitions = new HashMap<>();
    }

    public static TransitionMaker createTransition(HashSet<String[]> stringsOfTransitions){
        TransitionMaker transitionMaker = new TransitionMaker();
        for (String[] transition : stringsOfTransitions)
            transitionMaker.transitions.put(transition[0], transition[1]);
        return transitionMaker;
    }

    public String transitTo(String state, String alphabet) {
        return this.transitions.get(String.format("%s,%s", state, alphabet));
    }


}
