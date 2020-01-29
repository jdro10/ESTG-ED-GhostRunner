package Jogo;

import Estruturas.Network;

public class NetworkGame<T> extends Network<T> {

    public void setOneDirectionWeightPath(T v1, double weight, T v2){
        if (weight < 0.0) {
            throw new IllegalArgumentException("weight must be higher than 0");
        }

        int posv1 = super.getIndex(v1);
        int posv2 = super.getIndex(v2);

        this.weightMatrix[posv1][posv2] = weight;
    }
}