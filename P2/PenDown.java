/* subclasse que representa a instrucao penDown, que faz com que a tartaruga
volte a escrever
 */

public class PenDown extends TurtleStatement {
    public void run(Interpreter interpreter) {
        interpreter.runPenDown(this);
    }
}