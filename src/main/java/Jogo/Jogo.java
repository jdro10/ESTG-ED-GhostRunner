package Jogo;

import Estruturas.Network;

public class Jogo {

    private Mapa mapa;
    private Network<Aposento> graph;

    public Jogo(Mapa mapa) {
        this.mapa = mapa;
        this.initializeGraph();
    }



    public Network<Aposento> getGraph(){
        return this.graph;
    }

    public void initializeGraph() {

        this.graph = new Network();

        Aposento entrada = new Aposento("entrada", 0,  null);

        this.graph.addVertex(entrada);

        for (int i = 0; i < this.mapa.numeroSalas(); i++) {
            this.graph.addVertex(this.mapa.getAposento(i));
        }

        Aposento exterior = new Aposento("exterior", 0, null);

        this.graph.addVertex(exterior);


        this.graph.addEdge(this.graph.getVertex(3),this.graph.getVertex(2));

        /**
        for (int i = 0; i < this.graph.size(); i++) {
            for (int j = 1; j < this.graph.size() - 1; j++) {
                if (i != j) {
                    for (int k = 0; k < this.graph.getVertex(j).getNumeroLigacoes(); k++) {
                        if (this.graph.getVertex(i).getAposento() == this.graph.getVertex(j).getLigacao(k)) {
                            this.graph.addEdge(this.graph.getVertex(i), this.graph.getVertex(j), this.graph.getVertex(j).getFantasma());
                            this.graph.addEdge(this.graph.getVertex(j), this.graph.getVertex(i), 0);
                            this.graph.addEdge(this.graph.getVertex(i), this.graph.getVertex(j));
                            this.graph.addEdge(this.graph.getVertex(j), this.graph.getVertex(i));

                        }
                    }

                }

            }
            System.out.println(this.graph.toString());
        }
        */

        for(int i = 0 ; i < this.graph.size() ; i++){
            for (int j = 0; j < this.graph.size() ; j++) {
                for (int k = 0; k < this.graph.getVertex(j).getNumeroLigacoes(); k++) {
                    if (this.graph.getVertex(i).getAposento() == this.graph.getVertex(j).getLigacao(k)) {
                        this.graph.addEdge(this.graph.getVertex(i), this.graph.getVertex(j), this.graph.getVertex(j).getFantasma());
                        this.graph.addEdge(this.graph.getVertex(j), this.graph.getVertex(i), 0);
                        this.graph.addEdge(this.graph.getVertex(i), this.graph.getVertex(j));
                        this.graph.addEdge(this.graph.getVertex(j), this.graph.getVertex(i));
                    }
                }
            }
        }


    }

}
