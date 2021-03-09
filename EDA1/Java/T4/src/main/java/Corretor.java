import java.io.*;

public class Corretor {
    QuadHashTable<String> dic;
    File file;
    FileReader fr;
    BufferedReader br;
    String line;

    public Corretor(String src) throws IOException {
        dic = new QuadHashTable<>(998649);
        readFile(src);
    }

    public void readFile(String src) throws IOException {
        try {
            file = new File(src);
            fr = new FileReader(file);
            br = new BufferedReader(fr);
        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro nao encontrado.");
            e.printStackTrace();
        }
        while((line = br.readLine()) != null){
            dic.insere(line);
        }
        fr.close();
    }


    public void checkSpelling(String src) throws IOException {
        try {
            file = new File(src);
            fr = new FileReader(file);
            br = new BufferedReader(fr);
        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro nao encontrado.");
            e.printStackTrace();
        }
        int numLine = 0;
        while((line = br.readLine()) != null){
            numLine++;
            String[] splited = line.split("\\s+|\\,\\s*|\\;\\s*|\\.\\s*|\\:\\s*");
            for(int i = 0; i< splited.length; i++){
                if(dic.procurar(splited[i])==null || !dic.procurar(splited[i]).equals(splited[i])){
                    System.out.println("Linha: "+ numLine +"\nPalavra Errada: "+ splited[i]);
                    System.out.println("Sugestao Adicionar: "+sugestaoAdicionar(splited[i]));
                    System.out.println("Sugestao Remover: "+sugestaoRemover(splited[i]));
                    System.out.println("Trocar: "+sugestaoTrocar(splited[i]));
                    System.out.println();
                }
            }
        }



        fr.close();
    }

    public String sugestaoAdicionar(String palavra){
        String sugestao = "";

        for(int i = 0; i <= palavra.length(); i++) {
            for (int j = 97; j < 123; j++) {
                char newChar = (char)j;
                StringBuffer palAdd = new StringBuffer(palavra);
                palAdd.insert(i, newChar);

                String palAddStr = palAdd.toString();
                if (dic.procurar(palAddStr)!=null && dic.procurar(palAddStr).equals(palAddStr)){
                    sugestao = sugestao + " | " + palAdd;
                }
            }
        }
        return sugestao;
    }

    public String sugestaoRemover(String palavra){
        String sugestao = "";

        for(int i = 0; i < palavra.length(); i++) {
            StringBuilder palRem = new StringBuilder(palavra);
            palRem.deleteCharAt(i);

            String palRemStr = palRem.toString();
            if (dic.procurar(palRemStr)!=null && dic.procurar(palRemStr).equals(palRemStr)) {
                sugestao = sugestao + " | " + palRem;
            }
        }
        return sugestao;
    }

    public String sugestaoTrocar(String palavra){
        String sugestao, palTroca;
        sugestao = "";
        char[] palChar;

        for(int i=0; i<palavra.length()-1;i++){
            palChar = palavra.toCharArray();

            char temp = palChar[i];
            palChar[i] = palChar[i+1];
            palChar[i+1] = temp;

            palTroca = String.valueOf(palChar);
            if (dic.procurar(palTroca)!=null && dic.procurar(palTroca).equals(palTroca)) {
                sugestao = sugestao + " | " + palTroca;
            }
        }
        return sugestao;
    }

    public static void main(String[] args) throws IOException {
        Corretor corretor = new Corretor("src/main/resources/wordlist2020.txt");
        corretor.checkSpelling("src/main/resources/input.txt");
    }
}
