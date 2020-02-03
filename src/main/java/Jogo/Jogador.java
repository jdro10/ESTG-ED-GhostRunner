package Jogo;

import Interfaces.IJogador;

public class Jogador implements IJogador, Comparable<Jogador> {

    private String nome;
    private int pontuacao;

    public Jogador(String nome) {
        this.nome = nome;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public String getNome() {
        return nome;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    @Override
    public void mover() {

    }

    @Override
    public void danoRecebido() {

    }

    @Override
    public int compareTo(Jogador j) {
        return (j.getPontuacao() - this.pontuacao);
    }

    @Override
    public String toString() {
        return "Jogador{" +
                "nome='" + nome + '\'' +
                ", pontuacao=" + pontuacao +
                '}';
    }
}
