import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        Scanner input = new Scanner(System.in);

        /*
        System.out.println("Insert 2 BigInts: ");
        String bigintStr1 = input.nextLine();
        String bigintStr2 = input.nextLine();
        */

        BigInt num1 = new BigInt("1000000000000000000000000000000000000000000000000");
        BigInt num2 = new BigInt("490123591045920359012395405149051904593601961490");
        BigInt num3 = new BigInt("1001");

        System.out.println(num1.toString());
        System.out.println(num2.toString());
        System.out.println(num3.toString());

        System.out.println(num1.add(num2));
        System.out.println(num1.add(num3));



    }
}
