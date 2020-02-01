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

        this.graph.getShortestPath(entrada, exterior);
    }


    public boolean hasEdge(String aposento, int index) {

        if (this.graph.getVertex(index).getLigacoes().contains(aposento)) {
            return true;
        }

        return false;
    }

    public void mostrarOpcoes(int opcao){
        int index = this.mostrarIndiceDivisao();
        int j = 0;
        int[] array = new int[this.graph.size()];

        for(int i = 0 ; i < this.graph.size() ; i++){
            if(this.graph.getAdjMatrixIndex(index,i) && index != i){
                System.out.println(this.graph.getVertex(i).getAposento() + "! opção - " + j);
                j++;
                array[j] = i;
            }
        }

        if(opcao < j){
            this.localJogador = this.graph.getVertex(array[j]).getAposento();
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

    public void dano_recebido(){
        if(this.dificuldade == Dificuldade.FACIL){
            this.jogador.setPontuacao(1*1);
        }else if(this.dificuldade == Dificuldade.MEDIO){
            this.jogador.setPontuacao(1*2);
        }else{
            this.jogador.setPontuacao(1*3);
        }
    }

}
