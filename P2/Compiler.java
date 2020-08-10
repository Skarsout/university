import java.util.HashMap;
import java.util.Vector;

public class Compiler {

    HashMap<Character, TurtleStatement> ruleMap = new HashMap<>();

    public void addRule(Character letter, TurtleStatement statement){
        ruleMap.put(letter, statement);
    }


    protected TurtleStatement compile(Character c){
        return ruleMap.get(c);
    }



    protected Vector<TurtleStatement> compile(String word){
        final Vector<TurtleStatement> result = new Vector<>();
        for (int i = 0; i < word.length(); i++) {
            result.add(compile(word.charAt(i)));
        }
        return result;
    }
}