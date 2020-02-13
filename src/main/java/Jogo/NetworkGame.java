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


    public void addVertex(T vertex){
        if (this.numVertices == this.vertices.length) {
            expandCapacity();
        }

        this.vertices[this.numVertices] = vertex;

        for (int i = 0; i <= this.numVertices; i++) {
            this.adjMatrix[this.numVertices][i] = false;
            this.adjMatrix[i][this.numVertices] = false;
        }

        this.numVertices++;
    }

    private void expandCapacity(){
        T[] verticesTmp = ((T[]) new Object[this.vertices.length * 2]);

        double[][] weightMatrixTmp = new double[this.vertices.length*2][this.vertices.length*2];
        boolean[][] adjMatrixTmp = new boolean[this.vertices.length * 2][this.vertices.length * 2];

        for (int i = 0; i < this.vertices.length; i++) {
            for (int j = 0; j < this.vertices.length; j++) {
                weightMatrixTmp[i][j] = this.weightMatrix[i][j];
                adjMatrixTmp[i][j] = this.adjMatrix[i][j];
            }
            verticesTmp[i] = this.vertices[i];
        }

        this.vertices = verticesTmp;
        this.adjMatrix = adjMatrixTmp;
        this.weightMatrix = weightMatrixTmp;
    }

    public boolean getAdjMatrixIndex(int i, int j) {
        return this.adjMatrix[i][j];
    }
}
