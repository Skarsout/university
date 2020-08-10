/* Subclasse que representa a instrucao penUp que serve para a tartaruga se mover
sem escrever
 */

public class PenUp extends TurtleStatement {
    public void run(Interpreter interpreter) {
        interpreter.runPenUp( this);
    }
}