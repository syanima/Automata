package DFAtest;

import DFA.JsonParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.util.ArrayList;

public class JsonParserTest {
    @Test
    public void jsonParserShouldParseTheJSONString() throws ParseException {
        JsonParser jsonParser = new JsonParser();
        String jsonString = "[{\"should_run_pass_cases_and_fail_cases_for_machine_of_each_json_object\":\"odd number of zeroes\",\"type\":\"dfa\",\"tuple\":{\"states\":[\"q1\",\"q2\"],\"alphabets\":[\"1\",\"0\"],\"delta\":{\"q1\":{\"0\":\"q2\",\"1\":\"q1\"},\"q2\":{\"0\":\"q1\",\"1\":\"q2\"}},\"start-state\":\"q1\",\"final-states\":[\"q2\"]},\"pass-cases\":[\"0\",\"000\",\"00000\",\"10\",\"101010\",\"010101\"],\"fail-cases\":[\"00\",\"0000\",\"1001\",\"1010\",\"001100\"]}]";
        ArrayList<JSONObject> parsedJSON = jsonParser.parse(jsonString);
        for (JSONObject object:parsedJSON) {
            Object fiveTuple = object.get("tuple");
            jsonParser.fiveTuple((JSONObject) fiveTuple);
            jsonParser.transitionMaker((JSONObject) fiveTuple);
        }
    }
}
