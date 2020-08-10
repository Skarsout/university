import java.awt.Color;
import galapagos.*;
import java.util.List;



public class Draw implements Interpreter{

    Turtle rabbit = new Turtle();


    public Draw() {
        rabbit.penColor(Color.black);
        rabbit.speed(2000);
    }

    public void run(List<TurtleStatement> program){

        for(int i = 0 ; i<program.size(); i++){
            this.runTurtleStatement(program.get(i));
        }
    }

    public void runTurtleStatement(TurtleStatement statement){
        statement.run(this);
    }

    public void runForward(Forward statement){
        rabbit.move(statement.getDistance());
    }
    public void runTurn(Turn statement){
        rabbit.turn(statement.getAngle());
    }

    public void runPenUp(PenUp statement) {
        rabbit.penUp();
    }

    public void runPenDown(PenDown statement) {
        rabbit.penDown();
    }





}
