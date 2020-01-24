package ex1;

import java.util.Iterator;

/**
 * Graph represents an adjacency matrix implementation of a graph.
 *
 */
public class Graph<T> implements GraphADT<T> {
    protected final int DEFAULT_CAPACITY = 10;
    protected int numVertices; // number of vertices in the graph
    protected boolean[][] adjMatrix; // adjacency matrix
    protected T[] vertices; // values of vertices

    /**
     * Creates an empty graph.
     */
    public Graph() {
        this.numVertices = 0;
        this.adjMatrix = new boolean[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    /**
     * Inserts an edge between two vertices of the graph.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     */
    public void addEdge(T vertex1, T vertex2) {
        addEdge(getIndex(vertex1), getIndex(vertex2));
    }

    @Override
    public void removeEdge(T vertex1, T vertex2) {
        removeEdge(getIndex(vertex1),getIndex(vertex2));
    }

    @Override
    public Iterator iteratorBFS(T startVertex) throws EmptyException {
        return iteratorBFS(this.getIndex(startVertex));
    }

    @Override
    public Iterator iteratorDFS(T startVertex) throws EmptyCollectionException {
        return iteratorDFS(this.getIndex(startVertex));
    }


    public void removeEdge(int index1,int index2){
        if (indexIsValid(index1) && indexIsValid(index2))
        {
            adjMatrix[index1][index2] = false;
            adjMatrix[index2][index1] = false;
        }
    }


    public Iterator iteratorBFS(int startIndex) throws EmptyException {
        Integer x;
        LinkedQueue<Integer> traversalQueue = new LinkedQueue<Integer>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<T>();
        if (!indexIsValid(startIndex))
            return resultList.iterator();
        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++)
            visited[i] = false;

        traversalQueue.enqueue(new Integer(startIndex));
        visited[startIndex] = true;
        while (!traversalQueue.isEmpty())
        {
            x = traversalQueue.dequeue();
            resultList.addToRear(vertices[x.intValue()]);
            /** Find all vertices adjacent to x that have
             not been visited and queue them up */
            for (int i = 0; i < numVertices; i++)
            {
                if (adjMatrix[x.intValue()][i] && !visited[i])
                {
                    traversalQueue.enqueue(new Integer(i));
                    visited[i] = true;
                }
            }
        }
        return resultList.iterator();
    }



    public Iterator iteratorDFS(int startIndex) throws EmptyCollectionException {
        Integer x;
        boolean found;
        LinkedStack<Integer> traversalStack = new LinkedStack<Integer>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<T>();
        boolean[] visited = new boolean[numVertices];
        if (!indexIsValid(startIndex))
            return resultList.iterator();
        for (int i = 0; i < numVertices; i++)
            visited[i] = false;

        traversalStack.push(new Integer(startIndex));
        resultList.addToRear(vertices[startIndex]);
        visited[startIndex] = true;
        while (!traversalStack.isEmpty())
        {
            x = traversalStack.peek();
            found = false;
            /** Find a vertex adjacent to x that has not been visited
             and push it on the stack */
            for (int i = 0; (i < numVertices) && !found; i++)
            {
                if (adjMatrix[x.intValue()][i] && !visited[i])
                {
                    traversalStack.push(new Integer(i));
                    resultList.addToRear(vertices[i]);
                    visited[i] = true;
                    found = true;
                }
            }
            if (!found && !traversalStack.isEmpty())
                traversalStack.pop();
        }
        return resultList.iterator();
    }


    /******************************************************************
     Returns an iterator that contains the indices of the vertices
     that are in the shortest path between the two given vertices.
     ******************************************************************/
    private Iterator<Integer> iteratorShortestPathIndices (int startIndex, int targetIndex) throws EmptyException, EmptyCollectionException {
        int index = startIndex;
        int[] pathLength = new int[numVertices];
        int[] predecessor = new int[numVertices];
        LinkedQueue<Integer> traversalQueue = new LinkedQueue<Integer>();
        ArrayUnorderedList<Integer> resultList =
                new ArrayUnorderedList<Integer>();

        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex) ||
                (startIndex == targetIndex))
            return resultList.iterator();

        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++)
            visited[i] = false;

        traversalQueue.enqueue(new Integer(startIndex));
        visited[startIndex] = true;
        pathLength[startIndex] = 0;
        predecessor[startIndex] = -1;

        while (!traversalQueue.isEmpty() && (index != targetIndex))
        {
            index = (traversalQueue.dequeue()).intValue();

            /** Update the pathLength for each unvisited vertex adjacent
             to the vertex at the current index. */
            for (int i = 0; i < numVertices; i++)
            {
                if (adjMatrix[index][i] && !visited[i])
                {
                    pathLength[i] = pathLength[index] + 1;
                    predecessor[i] = index;
                    traversalQueue.enqueue(new Integer(i));
                    visited[i] = true;
                }
            }
        }
        if (index != targetIndex)  // no path must have been found
            return resultList.iterator();

        LinkedStack<Integer> stack = new LinkedStack<Integer>();
        index = targetIndex;
        stack.push(new Integer(index));
        do
        {
            index = predecessor[index];
            stack.push(new Integer(index));
        } while (index != startIndex);

        while (!stack.isEmpty())
            resultList.addToRear(((Integer)stack.pop()));

        return resultList.iterator();
    }

    /******************************************************************
     Returns an iterator that contains the shortest path between
     the two vertices.
     ******************************************************************/
    public Iterator<T> iteratorShortestPath(int startIndex, int targetIndex) throws EmptyException, EmptyCollectionException {

        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<T>();
        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex))
            return resultList.iterator();

        Iterator<Integer> it = iteratorShortestPathIndices(startIndex,
                targetIndex);
        while (it.hasNext())
            resultList.addToRear(vertices[((Integer)it.next()).intValue()]);
        return resultList.iterator();
    }

    /******************************************************************
     Returns an iterator that contains the shortest path between
     the two vertices.
     ******************************************************************/
    public Iterator<T> iteratorShortestPath(T startVertex, T targetVertex) throws EmptyException, EmptyCollectionException {

        return iteratorShortestPath(getIndex(startVertex), getIndex(targetVertex));
    }


    @Override
    public boolean isEmpty() {
        return size()==0;
    }

    @Override
    public boolean isConnected() throws EmptyException {
        if (isEmpty()){
            return false;
        }

        Iterator<T> it  = iteratorBFS(0);
        int count = 0;

        while(it.hasNext()){
            it.next();
            count++;
        }

        return count==this.numVertices;

    }

    @Override
    public int size() {
        return this.numVertices;
    }

    /**
     * Inserts an edge between two vertices of the graph.
     *
     * @param index1 the first index
     * @param index2 the second index
     */
    public void addEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = true;
            adjMatrix[index2][index1] = true;
        }
    }

    /**
     * Adds a vertex to the graph, expanding the capacity of the graph
     * if necessary. It also associates an object with the vertex.
     *
     * @param vertex the vertex to add to the graph
     */
    public void addVertex(T vertex) {
        if (this.numVertices == this.vertices.length)
            expandCapacity();
        this.vertices[this.numVertices] = vertex;
        for (int i = 0; i <= this.numVertices; i++) {
            this.adjMatrix[this.numVertices][i] = false;
            this.adjMatrix[i][this.numVertices] = false;
        }
        this.numVertices++;
    }

    @Override
    public void removeVertex(T vertex) {
        for(int i = 0 ; i < numVertices ; i++ ){
            if(vertex.equals(vertices[i])){
                removeVertex(i);
            }
        }
    }

    public void removeVertex(int index) {
        if(indexIsValid(index)){
            this.numVertices--;

            for(int i = index ; i < this.numVertices ; i++){
                this.vertices[i] = this.vertices[i+1];
            }

            for(int i = index ; i < this.numVertices ; i++){
                for(int j = 0 ; j <= this.numVertices ; j++){
                    this.adjMatrix[i][j] = this.adjMatrix[i+1][j];
                }
            }

            for(int i = index ; i < this.numVertices ; i++){
                for(int j = 0 ; j < this.numVertices ; j++){
                    this.adjMatrix[j][i] = this.adjMatrix[j][i+1];
                }
            }
        }
    }

    protected int getIndex(T vertex){
        for(int i = 0 ; i < this.vertices.length ; i++){
            if(this.vertices[i].equals(vertex)){
                return i;
            }
        }
        return -1;
    }

    protected boolean indexIsValid(int index){
        return index!=-1;
    }

    private void expandCapacity(){
        T [] verticesTmp = ((T[])new Object[this.vertices.length*2]);
        boolean [][] adjMatrixTmp = new boolean [this.vertices.length*2][this.vertices.length*2];

        for ( int i = 0 ; i < this.vertices.length ; i++) {
            for ( int j = 0 ; j < this.vertices.length ; j++){
                adjMatrixTmp[i][j] = this.adjMatrix[i][j];
            }
            verticesTmp[i] = this.vertices[i];
        }

        this.vertices = verticesTmp;
        this.adjMatrix = adjMatrixTmp;

    }
}
