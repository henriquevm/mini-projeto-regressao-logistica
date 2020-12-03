import java.util.Arrays;

/**
 * Classe suporte que guarda os gradientes de w e b, para atualizacao de seus pesos.
 *
 * @author Rafael D'Addio
 */
public class Gradientes {

    private final double[] dw; //dimensao == w, ou seja n_x
    private double db;

    public Gradientes(int tam) {
        dw = new double[tam];
        Arrays.fill(dw, 0);
    }

    /**
     * Retorna um dos valores do vetor dw
     *
     * @param pos a posicao no vetor
     * @return um valor de dw[pos]
     */
    public double getDw(int pos) {
        return dw[pos];
    }

    /**
     * Define o valor de uma posicao de dw
     *
     * @param pos a posicao no vetor
     * @param valor o novo valor
     */
    public void setDw(int pos, double valor) {
        dw[pos] = valor;
    }

    /**
     * @return o valor de db
     */
    public double getDb() {
        return db;
    }

    /**
     * @param db o novo valor de db
     */
    public void setDb(double db) {
        this.db = db;
    }

}
