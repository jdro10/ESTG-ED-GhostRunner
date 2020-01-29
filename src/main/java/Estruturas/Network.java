package Estruturas;

public class Network<T> extends Graph<T> implements NetworkADT<T> {

    protected double[][] weightMatrix;
    protected final double DEFAULT_WEIGHT = 1.0;

    public Network() {
        super();
        this.weightMatrix = new double[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
    }

    @Override
    public String toString() {

        String s = "";
        String result = "";

        for (int i = 0; i < this.size(); i++) {
            s += vertices[i].toString() + "\n";
        }

        result += "Adjacency Matrix\n";
        result += "----------------\n";

        result += "    ";
        for (int i = 0; i < numVertices; i++) {
            result += "" + i;
            if (i < 10)
                result += " ";
        }
        result += "\n\n";

        for (int i = 0; i < numVertices; i++) {
            result += "" + i + "\t";

            for (int j = 0; j < numVertices; j++) {
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
  
    public void setOneDirectionWeightPath(T v1, double weight, T v2) {
        if (weight < 0.0) {
            throw new IllegalArgumentException("weight must be higher than 0");
        }

        int posv1 = super.getIndex(v1);
        int posv2 = super.getIndex(v2);

        this.weightMatrix[posv1][posv2] = weight;
    }

    public void dijkstraShortestPath(int startIndex) throws InvalidIndexException {
        if(!super.indexIsValid(startIndex)){
            throw new InvalidIndexException("Invalid Index");
        }

        int[] distancesToOtherVertices = new int[super.size()];
        boolean[] visitedVertex = new boolean[super.size()];

        for (int i = 0; i < distancesToOtherVertices.length; i++) {
            distancesToOtherVertices[i] = Integer.MAX_VALUE;
            visitedVertex[i] = false;
        }

        distancesToOtherVertices[startIndex] = 0; //vertice inicial

        for(int i = 0; i < super.size(); i++){
            int minVertex = findMinDistance(distancesToOtherVertices, visitedVertex);
            visitedVertex[minVertex] = true;

            for(int j = 0; j < super.size(); j++){
                if(this.weightMatrix[minVertex][j] != 0 && !visitedVertex[j]){
                    int dist = (int) (distancesToOtherVertices[minVertex] + this.weightMatrix[minVertex][j]);
                    if(dist < distancesToOtherVertices[j]){
                        distancesToOtherVertices[j] = dist;
                    }
                }
            }
        }
    }

    public int findMinDistance(int[] distance, boolean[] visitedVertex){
        int minIndex = -1;
        int minVertex = Integer.MAX_VALUE;

        for(int i = 0; i < distance.length; i++){
            if(!visitedVertex[i] && distance[i] <= minVertex){
                minVertex = distance[i];
                minIndex = i;
            }
        }

        return minIndex;
    }
}