package Jogo;

import Estruturas.Network;

public class NetworkGame<T> extends Network<T> {

    /**
     * método responsável por atribuir o peso em apenas um dos sentidos do caminho, devido a este só perder vida quando entrar num aposento
     * com fantasma ( o contrário não se aplica, ou seja , sair de um aponsento com fantasma )
     * @param v1
     * @param weight
     * @param v2
     */
    public void setOneDirectionWeightPath(T v1, double weight, T v2) {
        if (weight < 0.0) {
            throw new IllegalArgumentException("weight must be higher than 0");
        }

        int posv1 = super.getIndex(v1);
        int posv2 = super.getIndex(v2);

        this.weightMatrix[posv1][posv2] = weight;
    }

    public boolean getAdjMatrixIndex(int i, int j) {
        return this.adjMatrix[i][j];
    }
}
