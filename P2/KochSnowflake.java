public class KochSnowflake implements LSystem{
    String initial;
    Character axiom;
    String rule;

    @Override
    public void setStart(String start) {
        initial = start;
    }

    @Override
    public void addRule(Character symbol, String word) {
        axiom = symbol;
        rule = word;
    }

    @Override
    public String iter(int n) {

        for (int i = 0; i < n; i++) {

            String next = "";


            for (int j = 0; j < initial.length(); j++){

                if (initial.charAt(j) == axiom) {

                    next = initial.replaceAll(String.valueOf(axiom), rule);

                }
            }

            initial = next;

        }

        return initial;
    }
}
