package regressaologistica;

import java.io.*;

/**
 *
 * @author Rafael D'Addio
 */
public class RegressaoLogistica {

    public static void main(String[] args) throws IOException {

        System.out.println("Iniciando...");

        String diretorioTeste, diretorioTreino;
        diretorioTeste = "C:\\Users\\Homirrimo\\Documents\\Engenharia de Computação\\TSI\\Jogo da velha\\GitHub\\mini-projeto-regressao-logistica\\dado\\teste.txt";
        diretorioTreino = "C:\\Users\\Homirrimo\\Documents\\Engenharia de Computação\\TSI\\Jogo da velha\\GitHub\\mini-projeto-regressao-logistica\\dado\\treino.txt";

        LineNumberReader lineCounter = new LineNumberReader(new InputStreamReader(new FileInputStream(diretorioTreino)));

        double X_treino[][] = new double[671][9];
        double Y_treino[] = new double[671];

        int linha = 0, coluna = 0;

        //pega a primeira linha da matriz
        String nextLine;
        do{
            //System.out.println(nextLine);
            nextLine = lineCounter.readLine();
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
                        X_treino[linha][coluna] = 2;
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
                        X_treino[linha][coluna] = 2;
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
                        X_treino[linha][coluna] = 2;
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
                        X_treino[linha][coluna] = 2;
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
                        X_treino[linha][coluna] = 2;
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
                        X_treino[linha][coluna] = 2;
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
                        X_treino[linha][coluna] = 2;
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
                        X_treino[linha][coluna] = 2;
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
                        X_treino[linha][coluna] = 2;
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

        System.out.println("Matriz \n");
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

        double[] Y_treino = {1, 1, 0, 0, 1, 1, 0, 0, 0};

        Modelo m = new Modelo(X_treino, Y_treino, X_treino, Y_treino);
        m.constroi_modelo(0.005, 1000, true);*/
    }

}
