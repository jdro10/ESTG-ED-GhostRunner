package Jogo;

import Estruturas.ArrayOrderedList;

public class Classificacao implements Comparable<Classificacao> {

    private String nomeMapa;
    private Jogador jogador;
    private ArrayOrderedList<Classificacao> arrayOrderedList;

    public Classificacao(String nomeMapa, Jogador jogador) {
        this.nomeMapa = nomeMapa;
        this.jogador = jogador;
        this.arrayOrderedList = new ArrayOrderedList<>();
    }

    @Override
    public int compareTo(Classificacao classificacao) {
        return (classificacao.jogador.getPontuacao() - this.jogador.getPontuacao());
    }

    @Override
    public String toString() {
        return "Classificacao{" +
                "mapa=" + this.nomeMapa +
                ", jogador=" + jogador.getNome() +
                ", pontuacao= " + jogador.getPontuacao() +
                '}';
    }
}