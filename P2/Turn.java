/* Sublcasse que representa a instrucao turn, ou seja para virar
a tartaruga
 */

public class Turn extends TurtleStatement {
    double angle;
    public Turn(double angle){
        this.angle = angle;
    }

    public double getAngle(){
        return angle;
    }
    public void run(Interpreter interpreter) {
        interpreter.runTurn( this);
    }
}