package regressaologistica;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Rafael D'Addio
 */
public class RegressaoLogistica {

    public static void main(String[] args) throws IOException {

        System.out.println("Iniciando...");

        String diretorioTeste, diretorioTreino, diretorioDados;

        diretorioTeste = "dado/teste.txt";
        diretorioTreino = "dado/treino.txt";
        diretorioDados = "dado/tic-tac-toe.data";

        //pega a primeira linha da matriz
        String nextLine;

        //*****************DADOS***********************
        ArrayList<String> dados = new ArrayList<>(); // Instância para armazenar temporáriamente os dados

        LineNumberReader lineCounterDados = new LineNumberReader(new InputStreamReader(new FileInputStream(diretorioDados)));
        nextLine = lineCounterDados.readLine();
        do{
            dados.add(nextLine);
            nextLine = lineCounterDados.readLine(); 
        }while(nextLine != null);
        lineCounterDados.close();

        gravarDados(dados, diretorioTeste, diretorioTreino);
        
        //*****************TREINO***********************
        LineNumberReader lineCounterTreino = new LineNumberReader(new InputStreamReader(new FileInputStream(diretorioTreino)));

        double X_treino[][] = new double[671][9];
        double Y_treino[] = new double[671];

        int linha = 0, coluna = 0;

        
        do{
            //System.out.println(nextLine);
            nextLine = lineCounterTreino.readLine();
            if (nextLine != null){
                String[] split = nextLine.split(",");

                //System.out.println("split "+split[0] + " "+split[1] + " "+split[2] + " "+split[3] + " "+split[4] + " "+ split[5] + " "+split[6] + " "+split[7] + " "+split[8] + " "+split[9]);

                switch (split[0]){
                    case "x":
                        X_treino[linha][coluna] = 1;
                        break;
                    case "o":
                        X_treino[linha][coluna] = 0;
                        break;
                    case "b":
                        X_treino[linha][coluna] = -1;
                        break;
                    default:
                        System.out.println("Número inválido");
                }
                coluna++;
                switch (split[1]){
                    case "x":
                        X_treino[linha][coluna] = 1;
                        break;
                    case "o":
                        X_treino[linha][coluna] = 0;
                        break;
                    case "b":
                        X_treino[linha][coluna] = -1;
                        break;
                    default:
                        System.out.println("Número inválido");
                }
                coluna++;
                switch (split[2]){
                    case "x":
                        X_treino[linha][coluna] = 1;
                        break;
                    case "o":
                        X_treino[linha][coluna] = 0;
                        break;
                    case "b":
                        X_treino[linha][coluna] = -1;
                        break;
                    default:
                        System.out.println("Número inválido");
                }
                coluna++;
                switch (split[3]){
                    case "x":
                        X_treino[linha][coluna] = 1;
                        break;
                    case "o":
                        X_treino[linha][coluna] = 0;
                        break;
                    case "b":
                        X_treino[linha][coluna] = -1;
                        break;
                    default:
                        System.out.println("Número inválido");
                }
                coluna++;
                switch (split[4]){
                    case "x":
                        X_treino[linha][coluna] = 1;
                        break;
                    case "o":
                        X_treino[linha][coluna] = 0;
                        break;
                    case "b":
                        X_treino[linha][coluna] = -1;
                        break;
                    default:
                        System.out.println("Número inválido");
                }
                coluna++;
                switch (split[5]){
                    case "x":
                        X_treino[linha][coluna] = 1;
                        break;
                    case "o":
                        X_treino[linha][coluna] = 0;
                        break;
                    case "b":
                        X_treino[linha][coluna] = -1;
                        break;
                    default:
                        System.out.println("Número inválido");
                }
                coluna++;
                switch (split[6]){
                    case "x":
                        X_treino[linha][coluna] = 1;
                        break;
                    case "o":
                        X_treino[linha][coluna] = 0;
                        break;
                    case "b":
                        X_treino[linha][coluna] = -1;
                        break;
                    default:
                        System.out.println("Número inválido");
                }
                coluna++;
                switch (split[7]){
                    case "x":
                        X_treino[linha][coluna] = 1;
                        break;
                    case "o":
                        X_treino[linha][coluna] = 0;
                        break;
                    case "b":
                        X_treino[linha][coluna] = -1;
                        break;
                    default:
                        System.out.println("Número inválido");
                }
                coluna++;
                switch (split[8]){
                    case "x":
                        X_treino[linha][coluna] = 1;
                        break;
                    case "o":
                        X_treino[linha][coluna] = 0;
                        break;
                    case "b":
                        X_treino[linha][coluna] = -1;
                        break;
                    default:
                        System.out.println("Número inválido");
                }
                coluna++;
                switch (split[9]){
                    case "positive":
                        Y_treino[linha] = 1;
                        break;
                    case "negative":
                        Y_treino[linha] = 0;
                        break;
                    default:
                        System.out.println("Número inválido para Y_treino");
                }

            }
            linha++;
            coluna=0;

        }while (nextLine != null);

        System.out.println("\n Matriz \n");
        for (int l = 0; l < X_treino.length; l++)  {
            for (int c = 0; c < X_treino[0].length; c++)     {
                System.out.print(X_treino[l][c] + " "); //imprime caracter a caracter
            }
            System.out.println(" "); //muda de linha
        }
        System.out.println(" "); //muda de linha
        System.out.println("vetor \n");

        for (int c = 0; c < Y_treino.length; c++)     {
            System.out.print(Y_treino[c] + " "); //imprime caracter a caracter
        }
        lineCounterTreino.close();
        //*****************TESTE***********************

        LineNumberReader lineCounterTeste = new LineNumberReader(new InputStreamReader(new FileInputStream(diretorioTeste)));

        double X_teste[][] = new double[287][9];
        double Y_teste[] = new double[287];

        linha = 0;
        coluna = 0;

        //pega a primeira linha da matriz
        do{
            //System.out.println(nextLine);
            nextLine = lineCounterTeste.readLine();
            if (nextLine != null){
                String[] split = nextLine.split(",");

                //System.out.println("split "+split[0] + " "+split[1] + " "+split[2] + " "+split[3] + " "+split[4] + " "+ split[5] + " "+split[6] + " "+split[7] + " "+split[8] + " "+split[9]);

                switch (split[0]){
                    case "x":
                        X_teste[linha][coluna] = 1;
                        break;
                    case "o":
                        X_teste[linha][coluna] = 0;
                        break;
                    case "b":
                        X_teste[linha][coluna] = -1;
                        break;
                    default:
                        System.out.println("Número inválido");
                }
                coluna++;
                switch (split[1]){
                    case "x":
                        X_teste[linha][coluna] = 1;
                        break;
                    case "o":
                        X_teste[linha][coluna] = 0;
                        break;
                    case "b":
                        X_teste[linha][coluna] = -1;
                        break;
                    default:
                        System.out.println("Número inválido");
                }
                coluna++;
                switch (split[2]){
                    case "x":
                        X_teste[linha][coluna] = 1;
                        break;
                    case "o":
                        X_teste[linha][coluna] = 0;
                        break;
                    case "b":
                        X_teste[linha][coluna] = -1;
                        break;
                    default:
                        System.out.println("Número inválido");
                }
                coluna++;
                switch (split[3]){
                    case "x":
                        X_teste[linha][coluna] = 1;
                        break;
                    case "o":
                        X_teste[linha][coluna] = 0;
                        break;
                    case "b":
                        X_teste[linha][coluna] = -1;
                        break;
                    default:
                        System.out.println("Número inválido");
                }
                coluna++;
                switch (split[4]){
                    case "x":
                        X_teste[linha][coluna] = 1;
                        break;
                    case "o":
                        X_teste[linha][coluna] = 0;
                        break;
                    case "b":
                        X_teste[linha][coluna] = -1;
                        break;
                    default:
                        System.out.println("Número inválido");
                }
                coluna++;
                switch (split[5]){
                    case "x":
                        X_teste[linha][coluna] = 1;
                        break;
                    case "o":
                        X_teste[linha][coluna] = 0;
                        break;
                    case "b":
                        X_teste[linha][coluna] = -1;
                        break;
                    default:
                        System.out.println("Número inválido");
                }
                coluna++;
                switch (split[6]){
                    case "x":
                        X_teste[linha][coluna] = 1;
                        break;
                    case "o":
                        X_teste[linha][coluna] = 0;
                        break;
                    case "b":
                        X_teste[linha][coluna] = -1;
                        break;
                    default:
                        System.out.println("Número inválido");
                }
                coluna++;
                switch (split[7]){
                    case "x":
                        X_teste[linha][coluna] = 1;
                        break;
                    case "o":
                        X_teste[linha][coluna] = 0;
                        break;
                    case "b":
                        X_teste[linha][coluna] = -1;
                        break;
                    default:
                        System.out.println("Número inválido");
                }
                coluna++;
                switch (split[8]){
                    case "x":
                        X_teste[linha][coluna] = 1;
                        break;
                    case "o":
                        X_teste[linha][coluna] = 0;
                        break;
                    case "b":
                        X_teste[linha][coluna] = -1;
                        break;
                    default:
                        System.out.println("Número inválido");
                }
                coluna++;
                switch (split[9]){
                    case "positive":
                        Y_teste[linha] = 1;
                        break;
                    case "negative":
                        Y_teste[linha] = 0;
                        break;
                    default:
                        System.out.println("Número inválido para Y_teste");
                }

            }
            linha++;
            coluna=0;

        }while (nextLine != null);

        System.out.println("Matriz teste\n");
        for (int l = 0; l < X_treino.length; l++)  {
            for (int c = 0; c < X_treino[0].length; c++)     {
                System.out.print(X_treino[l][c] + " "); //imprime caracter a caracter
            }
            System.out.println(" "); //muda de linha
        }
        System.out.println(" "); //muda de linha
        System.out.println("vetor teste\n");

        for (int c = 0; c < Y_treino.length; c++)     {
            System.out.print(Y_treino[c] + " "); //imprime caracter a caracter
        }
        System.out.println(" "); //muda de linha
        lineCounterTeste.close();
/*
        //teste de sanidade, para checar o funcionamento
        double[][] X_treino = {{4.5, 5.5, 3.0, 2.1, 2.2, 0.1},
        {2.5, 3.5, 1.0, 0.1, 1.2, 0.2},
        {-4.5, -5.5, -3.0, -2.1, -2.2, 1.0},
        {-2.5, -3.5, -1.0, -0.1, -1.2, 1.2},
        {0, 0, 0, 0, 0, 0},
        {-10.2, -4.5, -3.0, -12.7, -9.0, 0.3},
        {1, 2, 3, 4, 5, 0.33},
        {5, 4, 3, 2, 1, 0.12},
        {4.8, 5.8, 3.3, 2.4, 2.5, 1.0}};

        double[] Y_treino = {1, 1, 0, 0, 1, 1, 0, 0, 0};*/


                            //double[][] X_treino, double[] Y_treino, double[][] X_teste, double[] Y_teste
        //Modelo m = new Modelo(X_treino, Y_treino, X_treino, Y_treino);
        
        Modelo m = new Modelo(X_treino, Y_treino, X_teste, Y_teste);
        m.constroi_modelo(0.005, 1000, true);

    }
    public static void gravarDados(ArrayList<String> dados, String localTeste, String localTreino) throws IOException {
        int qtdDados = dados.size(); // tamanho dos dados
        int qtdTeste = (qtdDados*3)/10; // definindo tamanho do bloco de Teste (30%)
        Random r = new Random();
        ArrayList<String> teste = new ArrayList<>();
        ArrayList<String> treino = new ArrayList<>();
        
        int d = r.nextInt(3);
        int k = 2*d+1;
        for(int i=0; i<qtdDados; i++){
            if(k>0){
                if(d>0 && qtdTeste>0){
                    teste.add(dados.get(i));
                    d--;
                    k--;
                    qtdTeste--;
                } 
                else{
                    treino.add(dados.get(i));
                    k--;
                }     
            }
            else{
                treino.add(dados.get(i));
                d=r.nextInt(3)+1;
                k=2*d+1;
            }   
        }
        Arquivo.escritor(teste, localTeste);
        Arquivo.escritor(treino, localTreino);
    }

}
