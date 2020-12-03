import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Classe que implementa um modelo de regressao logistica. Esta implementacao realiza operacoes
 * elemento a elemento, fazendo uso de lacos for. As funcoes sofreram leves alteracoes devido a
 * implementacao considerar vetores linha ao inves de vetores coluna (usados na explicacao das
 * aulas).
 *
 * @author Rafael D'Addio
 */
public class Modelo {

    private double b; //vies
    private double[] w; //vetor de pesos, de dimensao n_x
    private final double[][] X_treino; //matriz de treinamento, de dimensao (m_treino, n_x)
    private final double[] Y_treino;   //vetor com rotulos das instancias, de dimensao m_treino
    private final double[][] X_teste;  //matriz de teste, de dimensao (m_teste, n_x)
    private final double[] Y_teste;    //vetor com rotulos das instancias, de dimensao m_teste

    DefaultCategoryDataset dataset = new DefaultCategoryDataset();



    public Modelo(double[][] X_treino, double[] Y_treino, double[][] X_teste, double[] Y_teste) {
        this.X_treino = X_treino;
        this.X_teste = X_teste;
        this.Y_treino = Y_treino;
        this.Y_teste = Y_teste;

        //inicializa pesos w e b com zero
        w = new double[X_treino[0].length];
        Arrays.fill(w, 0);
        b = 0;
    }

    /**
     * Calcula a funcao de transformacao linear z e a funcao de ativacao sigmoide(z).
     *
     * z = x*w^T +b y_hat = sigmoide(z)
     *
     * @param x_i uma instancia x^(i)
     * @return uma predicao em formato de probabilidade (y_hat)
     */
    private double sigmoide(double[] x_i) {
        double dot = 0;

        //calculando o produto escalar x*w^T
        for (int i = 0; i < x_i.length; i++) {
            dot += x_i[i] * w[i];
        }
        double z = dot + b;

        //calculando funcao sigmoide
        double sigmoide = 1 / (1 + Math.exp(-z));
        if (sigmoide == 1) {
            sigmoide = 0.999999;
        }
        if (sigmoide == 0) {
            sigmoide = 0.000001;
        }

        return sigmoide; //retorna y_hat
    }

    /**
     * Realiza a propagacao pra frente e pra tras. Implementa a funcao de custo J(w,b) e seu
     * gradiente.
     *
     * @param g o gradiente de uma epoca (dw e db medio de todas instancias)
     * @return o custo medio de uma epoca (da predicao de todas instancias)
     */
    private double propaga(Gradientes g) {
        double custo = 0;
        int m = X_treino.length; //numero de instancias

        for (int i = 0; i < m; i++) { //para cada instancia calcula e agrega o custo, dw e db, para depois tirar-se a media

            //propagacao pra frente
            double y_hat = sigmoide(X_treino[i]); //calculo a predicao
            custo += -(Y_treino[i] * Math.log(y_hat) + (1 - Y_treino[i]) * Math.log(1 - y_hat)); //L(y_hat^(i), y^(i)) = - (y^(i) * log(y_hat^(i)) + (1 - y^(i)) * log(1 - y_hat^(i)))

            //propagacao pra trás
            for (int j = 0; j < w.length; j++) { //calculo o dw[j] para cada peso w[j]
                double a = X_treino[i][j] * (y_hat - Y_treino[i]);
                g.setDw(j, (g.getDw(j) + a)); //dw[j] += dw[j] + x^(i)[j]*(y_hat^(i) - y^(i))
            }
            g.setDb(g.getDb() + (y_hat - Y_treino[i])); //db += (y_hat^(i) - y^(i))
        }

        //calcula valores medios 
        custo /= m;
        for (int j = 0; j < w.length; j++) {
            g.setDw(j, (g.getDw(j) / m));
        }
        g.setDb(g.getDb() / m);

        return custo;
    }

    /**
     * Atualiza os pesos w e b.
     *
     * @param g o gradiente para atualizacao
     * @param taxa_aprendizado a taxa de aprendizado
     */
    private void atualizaPesos(Gradientes g, double taxa_aprendizado) {

        for (int i = 0; i < w.length; i++) { //para cada w[i], w tem dimensao n_x
            w[i] = w[i] - taxa_aprendizado * g.getDw(i);
        }
        b = b - taxa_aprendizado * g.getDb();
    }

    /**
     * Otimiza w e b com base nos gradientes aprendidos, atraves do algoritmo de gradiente descente.
     *
     * @param taxa_aprendizado taxa de aprendizado a ser utilizada
     * @param num_iteracoes numero de interacoes (epocas) para aprendizado do modelo
     * @param print_custo se verdade, imprimir custo a cada 100 epocas
     * @return lista de todos os custos computados durante o treinamento. Utilize essa lista para
     * plotar a curva de aprendizado
     */
    private ArrayList<Double> treina(double taxa_aprendizado, double num_iteracoes, boolean print_custo) {
        ArrayList<Double> custos = new ArrayList<>();

        //para cada epoca
        for (int i = 0; i < num_iteracoes; i++) {
            Gradientes g = new Gradientes(w.length); //crio novos dw e db, dw.length == w.length

            double custo = propaga(g);
            atualizaPesos(g, taxa_aprendizado);

            //guarda o custo a cada 100 epocas
            if ((i % 100) == 0) {
                custos.add(custo);
            }

            //exibe o custo a cada 100 epocas, se print_custo == true
            if ((print_custo) && ((i % 50) == 0)) {
                //System.out.println("Custo depois de interacao " + i + ": " + custo);
                //grafico

                String nome = Integer.toString(i);

                dataset.addValue(custo, "Taxa", nome);
            }
        }

        return custos;
    }

    /**
     * Prediz se uma instancia eh 0 ou 1, utilizando os parametros w e b treinados atraves de
     * regressao logistica.
     *
     *
     * @param X conjunto de dados de dimensoes (m, n_x) -- instancias sao vistas como vetores-linhas
     * @return Y, um vetor com predicoes de dimensao m
     */
    private double[] prediz(double[][] X) {
        int m = X.length;
        double[] Y = new double[m];

        //para cada instancia
        for (int i = 0; i < m; i++) {
            Y[i] = sigmoide(X[i]); //calcula y_hat

            //converte probabilidades y_hat para predicoes
            if (Y[i] > 0.5) {
                Y[i] = 1;
            } else {
                Y[i] = 0;
            }
        }

        return Y;
    }

    /**
     * Constroi o modelo de regressao logistica com base nas funcoes anteriores.
     *
     * @param taxa_aprendizado taxa de aprendizado a ser utilizada
     * @param num_iteracoes numero de interacoes (epocas) para aprendizado do modelo
     * @param print_custo se verdade, imprimir custo a cada 100 epocas
     */
    public void constroi_modelo(double taxa_aprendizado, double num_iteracoes, boolean print_custo) {

        //treina o modelo, aprendendo e otimizando os parametros w e b
        ArrayList<Double> custos = treina(taxa_aprendizado, num_iteracoes, print_custo);

        //prediz rotulos de treinamento
        double[] Y_predicao_treino = prediz(X_treino);

        //apenas para verificacao, comentar depois
        for (int i = 0; i < Y_predicao_treino.length; i++) {

            // System.out.println(Y_predicao_treino[i]);
        }

        //prediz rotulos de teste
        double[] Y_predicao_teste = prediz(X_teste);

        //Exibe a taxa de acuracia do treino e do teste
        //Taxa de acuracia = 100 - MAE*100
        //Erro medio absoluto (MAE) ~~> MAE = 1/m * soma(|y_predito^(i) - y^(i)|)
        System.out.println("Acuracia de treino " + (100 - calculaMAE(Y_predicao_treino, Y_treino) * 100) + "%");
        System.out.println("Acuracia de teste " + (100 - calculaMAE(Y_predicao_teste, Y_teste) * 100) + "%");

        //Grafico

        JFreeChart chart = ChartFactory.createLineChart(
                "Custo X Interações",
                "Interações",
                "Custo",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        chart.setBackgroundPaint(Color.white);
        chart.getTitle().setPaint(Color.black);
        CategoryPlot p = chart.getCategoryPlot();
        p.setForegroundAlpha(1f);
        p.setRangeGridlinePaint(Color.black);
        p.setDomainGridlinesVisible(true);
        p.setDomainGridlinePaint(Color.black);
        CategoryItemRenderer renderer = p.getRenderer();
        renderer.setSeriesPaint(0, Color.blue);

        //renderer.setBaseStroke(new BasicStroke(2.0f));

        renderer.setSeriesStroke(0, new BasicStroke(3f));

        //renderer.setAutoPopulateSeriesStroke(false);

        //renderer.setSeriesShape(0, 0.3f);
        //renderer.setSeriesPaint(1, Color.green);
        ChartFrame frame1 = new ChartFrame("Custo X Interações", chart);
        frame1.setVisible(true);
        frame1.setSize(1000,500);

    }

    /**
     * Calcula o erro medio absoluto (MAE).
     *
     * @param Y_predicao vetor com as predicoes, dimensao m
     * @param Y vetor com os rotulos reais, dimensao m
     * @return o valor do erro medio absoluto
     */
    private double calculaMAE(double[] Y_predicao, double[] Y) {
        double mae = 0;

        for (int i = 0; i < Y.length; i++) {
            mae += Math.abs(Y_predicao[i] - Y[i]);
        }
        mae /= Y.length;

        return mae;
    }

}
