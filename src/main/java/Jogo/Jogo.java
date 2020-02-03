package Jogo;

import Estruturas.InvalidIndexException;
import Estruturas.Network;
import Enum.Dificuldade;
import Exceptions.MapaException;

import java.util.Iterator;

public class Jogo {

    private Mapa mapa;
    private NetworkGame<Aposento> graph;
    private Jogador jogador;
    private String localJogador;
    private Dificuldade dificuldade;
    private int dificuldadeMultiplicador;
    private Aposento entrada;
    private Aposento exterior;
    private final String POSICAO_INICIAL = "entrada";

    public Jogo(Mapa mapa, Dificuldade dificuldade) throws InvalidIndexException, MapaException {
        this.mapa = mapa;
        this.graph = new NetworkGame();
        this.setDificuldadeMultiplicador(dificuldade);
        this.initializeGraph();
        this.localJogador = POSICAO_INICIAL;
        this.localJogador = "entrada";
        this.jogador = new Jogador("xPromate");
        this.jogador.setPontuacao(this.mapa.getPontos());
    }

    public String getLocalJogador(){
        return localJogador;
    }

    private void initializeGraph() throws MapaException {

        if(mapa.temEntradaOuExterior()){
            throw new MapaException("mapa inválido");
        }

        if(mapa.temLigacaoEntrada()){
            throw new MapaException("mapa inválido");
        }

        if(mapa.temLigacaoExterior()){
            throw new MapaException("mapa inválido");
        }


        this.entrada = new Aposento("entrada", 0, null);
        this.exterior = new Aposento("exterior", 0, null);

        this.graph.addVertex(entrada);

        for (int i = 0; i < this.mapa.numeroSalas(); i++) {
            this.graph.addVertex(this.mapa.getAposento(i));
        }

        this.graph.addVertex(exterior);

        for (int i = 0; i < this.graph.size(); i++) {
            for (int j = 0; j < this.graph.size(); j++) {
                if (hasEdge(this.graph.getVertex(i).getAposento(), j)) {
                    this.graph.addEdge(this.graph.getVertex(i), this.graph.getVertex(j));
                    this.graph.setOneDirectionWeightPath(this.graph.getVertex(i), this.graph.getVertex(j).getFantasma(), this.graph.getVertex(j));
                }
            }
        }
    }

    public void simulacaoJogo() throws InvalidIndexException {
        Iterator<Aposento> it = this.graph.getShortestPath(this.entrada, this.exterior);

        while (it.hasNext()) {
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

    public void mostrarHipoteses(){
        int index = this.mostrarIndiceDivisao();
        int j = 0;

        for(int i = 0 ; i < this.graph.size() ; i++){
            if (this.graph.getAdjMatrixIndex(index, i) && index != i) {
                System.out.println(this.graph.getVertex(i).getAposento() + "! opção - " + j);
                j++;
            }
        }
    }

    public void escolheOpcoes(int opcao) {
        int index = this.mostrarIndiceDivisao();
        int j = 0;
        int[] array = new int[this.graph.size()];

        for (int i  = 0; i < this.graph.size(); i++) {
            if (this.graph.getAdjMatrixIndex(index, i) && index != i) {
                array[j] = i;
                j++;
            }
        }

        if (opcao <= j) {
            this.localJogador = this.graph.getVertex(array[opcao]).getAposento();
            this.dano_recebido(array[j]);
        }

    }

    public int mostrarIndiceDivisao() {
        for (int i = 0; i < this.graph.size(); i++) {
            if (this.localJogador.equals(this.graph.getVertex(i).getAposento())) {
                return i;
            }
        }

        return -1;
    }

    private void dano_recebido(int indiceDoVertice) {
        this.jogador.setPontuacao(this.jogador.getPontuacao()-(this.graph.getVertex(indiceDoVertice).getFantasma()*this.dificuldadeMultiplicador));
    }

    public void setDificuldadeMultiplicador(Dificuldade dificuldade) {
        this.dificuldade = dificuldade;

        if (this.dificuldade == Dificuldade.BASICO) {
            this.dificuldadeMultiplicador = 1;
        } else if (this.dificuldade == Dificuldade.NORMAL) {
            this.dificuldadeMultiplicador = 2;
        } else if (this.dificuldade == Dificuldade.DIFICIL) {
            this.dificuldadeMultiplicador = 3;
        }
    }

    public void setJogador(Jogador jogador){
        this.jogador = jogador;
    }
}
