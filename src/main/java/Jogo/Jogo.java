package Jogo;

import Estruturas.InvalidIndexException;
import Estruturas.Network;
import Enum.Dificuldade;

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

        //VAI SER APAGADO
        for(int i = 0 ; i < this.graph.size() ; i++){
            for(int j = 0 ; j < this.graph.size() ; j++ ){
                if(this.graph.getWeightMatrixIndex(i,j) == 0.0){
                    this.graph.setWeightMatrixIndex(i,j,1);
                }
            }
        }
    }

    public void dijkstra() throws InvalidIndexException {
        this.graph.dijkstraShortestPath(0);
    }

    public boolean hasEdge(String aposento, int index) {

        if (this.graph.getVertex(index).getLigacoes().contains(aposento)) {
            return true;
        }

        return false;
    }

    public void mostrarOpcoes(){
        int index = this.mostrarIndiceDivisao();

        for(int i = 0 ; i < this.graph.size() ; i++){
            if(this.graph.getAdjMatrixIndex(index,i)){
                System.out.println(this.graph.getVertex(i).getAposento());
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
