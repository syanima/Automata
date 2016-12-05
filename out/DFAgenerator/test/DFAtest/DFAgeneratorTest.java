package DFAtest;

import DFA.DFAgenerator;
import DFA.TransitionMaker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;


public class DFAgeneratorTest {

    private DFAgenerator dfaGenerator;


    @Before
    public void init() {

        HashSet initialStates = new HashSet<String>();
        HashSet setOfStates = new HashSet<String>();
        HashSet finalStates = new HashSet<String>();
        HashSet setOfAlphabets = new HashSet<String>();
        setOfStates.add("q1");
        setOfStates.add("q2");
        setOfStates.add("q3");
        finalStates.add("q3");
        initialStates.add("q1");
        setOfAlphabets.add('1');
        setOfAlphabets.add('0');

        HashMap<String, HashSet<String>> fiveTuple = new HashMap<>();
        fiveTuple.put("finalStates", finalStates);
        fiveTuple.put("initialStates", initialStates);
        fiveTuple.put("setOfAlphabets", setOfAlphabets);
        fiveTuple.put("setOfStates", setOfStates);

        HashSet<String[]> transitions = new HashSet<String[]>();
        transitions.add(new String[]{"q1,0", "q2"});
        transitions.add(new String[]{"q1,1", "q1"});
        transitions.add(new String[]{"q2,0", "q2"});
        transitions.add(new String[]{"q2,1", "q3"});
        transitions.add(new String[]{"q3,0", "q3"});
        transitions.add(new String[]{"q3,1", "q3"});

        TransitionMaker transitionMaker = TransitionMaker.createTransition(transitions);
        dfaGenerator = new DFAgenerator(fiveTuple, transitionMaker);
    }

    @Test
    public void DFAgeneratorShouldDetectAValidString() {

        Assert.assertTrue(dfaGenerator.isDetectable("101"));
        Assert.assertTrue(dfaGenerator.isDetectable("11101"));
        Assert.assertTrue(dfaGenerator.isDetectable("01010"));
    }

    @Test
    public void DFAgeneratorShouldNotDetectAnInvalidString() {

        Assert.assertFalse(dfaGenerator.isDetectable("10"));
        Assert.assertFalse(dfaGenerator.isDetectable("0000"));
        Assert.assertFalse(dfaGenerator.isDetectable("2"));
        Assert.assertFalse(dfaGenerator.isDetectable(""));
    }

}