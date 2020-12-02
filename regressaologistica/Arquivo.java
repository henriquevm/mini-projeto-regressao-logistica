package regressaologistica;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Arquivo {
    public static void escritor(ArrayList<String> linhas, String caminho) throws IOException {
        
        FileWriter arq = new FileWriter(caminho);
        PrintWriter gravarArq = new PrintWriter(arq);

        for(int i=0; i<linhas.size(); i++){
            gravarArq.println(linhas.get(i));
        }
        arq.close();
        System.out.println("Gravação do Arquivo Realizada com Sucesso!");
        System.out.println("Nome do Arquivo: "+ caminho);
    }  
}
