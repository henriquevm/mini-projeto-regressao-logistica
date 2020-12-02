package regressaologistica;

/**
 *
 * @author Rafael D'Addio
 */
public class RegressaoLogistica {

    public static void main(String[] args) {
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
        m.constroi_modelo(0.005, 1000, true);
    }

}
