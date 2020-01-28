package Jogo;

import Estruturas.Network;

public class Jogo {

    private Mapa mapa;
    private Network<Aposento> graph;

    public Jogo(Mapa mapa) {
        this.mapa = mapa;
        this.initializeGraph();
    }


    public Network<Aposento> getGraph() {
        return this.graph;
    }

    public void initializeGraph() {

        this.graph = new Network();

        Aposento entrada = new Aposento("entrada", 0, null);

        this.graph.addVertex(entrada);

        for (int i = 0; i < this.mapa.numeroSalas(); i++) {
            this.graph.addVertex(this.mapa.getAposento(i));
        }

        Aposento exterior = new Aposento("exterior", 0, null);

        this.graph.addVertex(exterior);

        for (int i = 0; i < this.graph.size(); i++) {
            for (int j = 1; j < this.graph.size() - 1; j++) {
                if (hasEdge(this.graph.getVertex(i).getAposento(), j)) {
                    this.graph.addEdge(this.graph.getVertex(i), this.graph.getVertex(j));
                }
            }
        }


    }

    public boolean hasEdge(String aposento, int index) {

        if (this.graph.getVertex(index).getLigacoes().contains(aposento)) {
            return true;
        }

        return false;
    }

}
