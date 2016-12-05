package DFAtest;

import DFA.TransitionMaker;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;

public class TransitionMakerTest {

    @Test
    public void transitToShouldReturnTheNewStateOfGivenAlphabetAndCurrentStateOfAlphabet() {
        HashSet<String[]> transitions = new HashSet<String[]>();
        transitions.add(new String[]{"q1,0", "q2"});
        transitions.add(new String[]{"q1,1", "q1"});
        transitions.add(new String[]{"q2,0", "q2"});
        transitions.add(new String[]{"q2,1", "q3"});
        transitions.add(new String[]{"q3,0", "q3"});
        transitions.add(new String[]{"q3,1", "q3"});
        TransitionMaker transitionMaker = TransitionMaker.createTransition(transitions);
        assertEquals(transitionMaker.transitTo("q2", "0"), "q2");
    }

}
