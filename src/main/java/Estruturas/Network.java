package Estruturas;

public class Network<T> extends Graph<T> implements NetworkADT<T> {

    protected double[][] weightMatrix;
    protected final double DEFAULT_WEIGHT = 1.0;

    public Network() {
        super();
        this.weightMatrix = new double[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
    }

    @Override
    public String toString(){

        String s = "";
        String result = "";

        for(int i = 0 ; i < this.size() ; i++){
            s += vertices[i].toString() + "\n";
        }

        result += "Adjacency Matrix\n";
        result += "----------------\n";

        result += "    ";
        for (int i = 0; i < numVertices; i++)
        {
            result += "" + i;
            if (i < 10)
                result += " ";
        }
        result += "\n\n";

        for (int i = 0; i < numVertices; i++)
        {
            result += "" + i + "\t";

            for (int j = 0; j < numVertices; j++)
            {
                result += this.weightMatrix[i][j] + "\t";
            }
            result += "\n";
        }


        return result;
    }

    @Override
    public void addEdge(T vertex1, T vertex2, double weight) {

        if (weight < 0.0) {
            throw new IllegalArgumentException("weight must be higher than 0");
        }
        super.addEdge(vertex1, vertex2);
        this.setEdgeWeight(vertex1, weight, vertex2);
    }

    public void setEdgeWeight(T v1, double weight, T v2) {
        if (weight < 0.0) {
            throw new IllegalArgumentException("weight must be higher than 0");
        }

        int posv1 = super.getIndex(v1);
        int posv2 = super.getIndex(v2);

        this.weightMatrix[posv1][posv2] = weight;
        this.weightMatrix[posv2][posv1] = weight;
    }

    @Override
    public double shortestPathWeight(T vertex1, T vertex2) {
        return 0;
    }
}