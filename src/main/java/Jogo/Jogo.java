package Jogo;

import Estruturas.InvalidIndexException;
import Estruturas.Network;
import Enum.Dificuldade;

import java.util.Iterator;

public class Jogo {

    private Mapa mapa;
    private NetworkGame<Aposento> graph;
    private Jogador jogador;
    private Dificuldade dificuldade;
    private String localJogador;

    public Jogo(Mapa mapa,String nome, Dificuldade dificuldade) throws InvalidIndexException {
        this.mapa = mapa;
        this.initializeGraph();
        this.jogador = new Jogador(nome);
        this.dificuldade = dificuldade;
        this.localJogador = "entrada";
    }


    public Network<Aposento> getGraph() {
        return this.graph;
    }

    public void initializeGraph() throws InvalidIndexException {

        this.graph = new NetworkGame();

        Aposento entrada = new Aposento("entrada", 0, null);

        this.graph.addVertex(entrada);

        for (int i = 0; i < this.mapa.numeroSalas(); i++) {
            this.graph.addVertex(this.mapa.getAposento(i));
        }

        Aposento exterior = new Aposento("exterior", 0, null);

        this.graph.addVertex(exterior);

        for (int i = 0; i < this.graph.size() ; i++) {
            for (int j = 0; j < this.graph.size() ; j++) {
                if (hasEdge(this.graph.getVertex(i).getAposento(), j)) {
                    this.graph.addEdge(this.graph.getVertex(i), this.graph.getVertex(j));
                    this.graph.setOneDirectionWeightPath(this.graph.getVertex(i), this.graph.getVertex(j).getFantasma(), this.graph.getVertex(j));
                }
            }
        }

        Iterator it = this.graph.getShortestPath(entrada, exterior);

        while(it.hasNext()){
            System.out.println(it.next());
        }

        System.out.println("Peso do caminho mais curto: " + this.graph.shortestPathWeight(entrada, exterior));
    }


    public boolean hasEdge(String aposento, int index) {

        if (this.graph.getVertex(index).getLigacoes().contains(aposento)) {
            return true;
        }

        return false;
    }

    public void mostrarOpcoes(){
        int index = this.mostrarIndiceDivisao();
        int j = 0;

        for(int i = 0 ; i < this.graph.size() ; i++){
            if(this.graph.getAdjMatrixIndex(index,i)){
                System.out.println(this.graph.getVertex(i).getAposento() + "! opção - " + j);
                j++;
            }
        }
    }

    public int mostrarIndiceDivisao(){
        for(int i = 0 ; i < this.graph.size() ; i++){
            if(this.localJogador.equals(this.graph.getVertex(i).getAposento())){
                return i;
            }
        }

        return -1;
    }

    public void mexe(int pos){

    }

    public void dano_recebido(){

    }

}
