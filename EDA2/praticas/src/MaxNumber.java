import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MaxNumber{

    public static void main(String[] args) throws IOException {
        int c, s, max;
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        c=Integer.parseInt(input.readLine());

        max = Integer.MIN_VALUE;
        for(int i = 0; i<c; i++){
            StringTokenizer linha = new StringTokenizer(input.readLine(), " ");
            s = linha.countTokens();
            int[] numeros = new int[s];

            for(int l = 0; l<s; l++) {
                numeros[l] = Integer.parseInt(linha.nextToken());
                if(max<numeros[l]){
                    max = numeros[l];
                }
            }
        }
        System.out.print(max);
    }
}
