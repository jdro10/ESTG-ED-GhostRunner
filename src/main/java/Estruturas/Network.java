package ex1;

public class  Network<T> extends Graph<T> implements NetworkADT<T> {

    protected double[][] weightMatrix;
    protected final double DEFAULT_WEIGHT = 1.0;


    @Override
    public void addEdge(T vertex1, T vertex2, double weight) {

        if ( weight < 0.0 ){
            throw new IllegalArgumentException("o peso da travessia  tem que ser maior que 0");
        }

        super.addEdge(vertex1,vertex2);

        this.setEdgeWeight(vertex1,weight,vertex2);
    }

    public void setEdgeWeight(T v1,double weight,T v2){

        if(weight < 0.0){
            throw new IllegalArgumentException("o peso da travessia  tem que ser maior que 0");
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
