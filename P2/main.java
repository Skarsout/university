import galapagos.Turtle;

import java.util.Scanner;
import java.util.Vector;

public class main {
    public static void main(String[] args){
        System.out.println("Bem-vindo ao gerador de Sistemas-L");
        System.out.println("Escolha o pretendido Sistema-L:");
        System.out.println("1 - Koch Curve");
        System.out.println("2 - Koch Snowflake");
        System.out.println("3 - Sierpinski Triangle");
        System.out.println("4 - Sierpinski ArrowHead");
        System.out.println("5 - Dragon Curve ");
        System.out.println(" ");

        Scanner scanner = new Scanner (System.in);
        int selection = scanner.nextInt();

        System.out.println("E o número de iteracoes? ");
        int iteracoes = scanner.nextInt();

        Draw window = new Draw();

        switch (selection) {

            case (1):
                
                LSystem kochCurve = new KochCurve();
                kochCurve.setStart("F");
                kochCurve.addRule('F', "F+F-F-F+F");
                Compiler koch = new Compiler();
                koch.addRule('F', new Forward(5.0));
                koch.addRule('-', new Turn(-90.0));
                koch.addRule('+', new Turn(90.0));

                Vector<TurtleStatement> kochCur = koch.compile(kochCurve.iter(iteracoes));

                window.run(kochCur);

                break;

            case (2):

                LSystem kochSnowflake = new KochSnowflake();
                kochSnowflake.setStart("F--F--F");
                kochSnowflake.addRule('F',"F+F--F+F");
                Compiler snow = new Compiler();
                snow.addRule('F', new Forward(5.0));
                snow.addRule('-', new Turn(-60.0));
                snow.addRule('+', new Turn(60.0));

                Vector<TurtleStatement> snowflake = snow.compile(kochSnowflake.iter(iteracoes));

                window.run(snowflake);

                break;

            case (3):

                LSystem sierpT = new SierpinskiTriangle();
                sierpT.setStart("F-G-G");
                sierpT.addRule('F', "F-G+F+G-F");
                sierpT.addRule('G', "GG");

                Compiler triangle = new Compiler();
                triangle.addRule('F', new Forward(5.0));
                triangle.addRule('G', new Forward(5.0));
                triangle.addRule('+', new Turn(120.0));
                triangle.addRule('-', new Turn(-120.0));

                Vector<TurtleStatement> serpTriangle = triangle.compile(sierpT.iter(iteracoes));

                window.run(serpTriangle);

                break;

            case (4):

                LSystem sierpA = new SierpinskiArrow();
                sierpA.setStart("A");
                sierpA.addRule('A',"B-A-B");
                sierpA.addRule('B',"A+B+A");

                Compiler arrow = new Compiler();
                arrow.addRule('A', new Forward(5.0));
                arrow.addRule('B', new Forward(5.0));
                arrow.addRule('+', new Turn(60.0));
                arrow.addRule('-', new Turn(-60.0));

                Vector<TurtleStatement> arrowSerp = arrow.compile(sierpA.iter(iteracoes));


                window.run(arrowSerp);

                break;

            case (5):

                LSystem dragonCurve = new DragonCurve();
                dragonCurve.setStart("FX");
                dragonCurve.addRule('X',"X+YF+");
                dragonCurve.addRule('Y',"−FX−Y");
                Compiler dragon = new Compiler();

                dragon.addRule('F', new Forward(5.0));
                dragon.addRule('+', new Turn(-90.0));
                dragon.addRule('-', new Turn(90.0));
                dragon.addRule('X', new Forward(0.0));
                dragon.addRule('Y', new Forward(0.0));

                Vector<TurtleStatement> dragonCur = dragon.compile(dragonCurve.iter(iteracoes));

                window.run(dragonCur);

                break;
        }

        scanner.close();

    }
}
