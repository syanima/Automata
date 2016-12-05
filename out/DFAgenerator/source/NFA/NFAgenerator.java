package NFA;

import java.util.HashMap;
import java.util.HashSet;

public class NFAgenerator {
    private Object[] state;
    private HashMap<String, HashSet<String>> fiveTuple;
    private TransitionMaker transitionMaker;

    public NFAgenerator(HashMap<String, HashSet<String>> fiveTuple, TransitionMaker transitionMaker) {
        this.transitionMaker = transitionMaker;
        this.fiveTuple = fiveTuple;
        this.state = fiveTuple.get("initialStates").toArray();
    }


    public boolean isDetectable(String testString) {
        String[] alphabets = testString.split("");
        HashSet<String> states = new HashSet<>();
        for (String alphabet : alphabets) {
            for (Object state : this.state) {
                String[] result = this.transitionMaker.transitTo(state.toString(), alphabet);
                if (result != null) {
                    for (String newState : result) {
                        states.add(newState);
                    }
                }
            }
        }

        this.state = states.toArray();
        for (Object state : this.state) {
            if (fiveTuple.get("finalStates").contains(state.toString()))
                return true;
        }
        return false;
    }
}



