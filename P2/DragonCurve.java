public class DragonCurve implements LSystem{

    String initial;
    Character[] axioms = new Character[2];
    String[] rules = new String[2];
    int count = 0;

    @Override
    public void setStart(String start) {
        initial = start;
    }

    @Override
    public void addRule(Character symbol, String word) {
        axioms[count] = symbol;
        rules[count] = word;
        count++;
    }

    @Override
    public String iter(int n) {

        rules[0] = rules[0].toLowerCase();    //Para que as letras não se troquem de uma só vez

        for (int i = 0; i < n; i++) {

            for(int j = 0; j < 2; j++) {

                if (axioms[j] == 'X') {break;}
                if (axioms[j] == 'Y') {break;}

                initial = initial.replaceAll(String.valueOf(axioms[j]), rules[j]);

            }


            initial = initial.toUpperCase();    //Restaurar a palavra

        }

        return initial;
    }
}
