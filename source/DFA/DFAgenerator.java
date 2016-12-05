package DFA;

import java.util.HashMap;
import java.util.HashSet;

public class DFAgenerator {
    private String state;
    private HashMap<String, HashSet<String>> fiveTuple;
    private TransitionMaker transitionMaker;

    public DFAgenerator(HashMap<String, HashSet<String>> fiveTuple, TransitionMaker transitionMaker) {
        this.transitionMaker = transitionMaker;
        this.fiveTuple = fiveTuple;
        this.state = String.valueOf(fiveTuple.get("initialStates").toArray()[0]);
    }

    public boolean isDetectable(String testString) {
        String[] alphabets = testString.split("");
        for (String alphabet : alphabets)
            this.state = this.transitionMaker.transitTo(this.state, alphabet);
        return fiveTuple.get("finalStates").contains(this.state);
    }
}
