package NFAtest;

import NFA.NFAgenerator;
import NFA.TransitionMaker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;

public class NFAgeneratorTest {

    private NFAgenerator nfaGenerator;


    @Before
    public void initialize() {
        HashSet<String> initialStates = new HashSet<>();
        HashSet setOfStates = new HashSet<String>();
        HashSet finalStates = new HashSet<String>();
        HashSet setOfAlphabets = new HashSet<String>();
        setOfStates.add("q1");
        setOfStates.add("q2");
        setOfStates.add("q3");
        finalStates.add("q1");
        initialStates.add("q1");
        initialStates.add("q3");
        setOfAlphabets.add('a');
        setOfAlphabets.add('b');


        HashMap<String, HashSet<String>> fiveTuple = new HashMap<>();
        fiveTuple.put("finalStates", finalStates);
        fiveTuple.put("setOfStates", setOfStates);
        fiveTuple.put("initialStates", initialStates);
        fiveTuple.put("setOfAlphabets", setOfAlphabets);

        HashSet<String[]> transitions = new HashSet<>();
        transitions.add(new String[]{"q1,b", "q2"});
        transitions.add(new String[]{"q3,a", "q1,q3"});
        transitions.add(new String[]{"q2,b", "q3"});
        transitions.add(new String[]{"q2,a", "q2,q3"});
        TransitionMaker transitionMaker = TransitionMaker.createTransition(transitions);

        nfaGenerator = new NFAgenerator(fiveTuple, transitionMaker);
    }

    @Test
    public void NFAgeneratorShouldDetectAValidString() {

        Assert.assertTrue(nfaGenerator.isDetectable("bba"));
        Assert.assertTrue(nfaGenerator.isDetectable("aab"));
        Assert.assertTrue(nfaGenerator.isDetectable("a"));
    }

    @Test
    public void NFAgeneratorShouldNotDetectAnInvalidString() {

        Assert.assertFalse(nfaGenerator.isDetectable("d"));
        Assert.assertFalse(nfaGenerator.isDetectable("c"));
        Assert.assertFalse(nfaGenerator.isDetectable("2"));
        Assert.assertFalse(nfaGenerator.isDetectable(""));
    }



}


