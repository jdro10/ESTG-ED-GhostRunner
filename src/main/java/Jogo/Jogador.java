package Jogo;

import Interfaces.IJogador;

public class Jogador implements IJogador {

    private String nome;
    private int pontuacao;
    private final int PONTUACAO_INICIAL = 100;

    public Jogador() {
        this.nome = null;
        this.pontuacao = PONTUACAO_INICIAL;
    }

    public boolean setNome(String nome) {
        if (!this.nome.equals("")) {
            this.nome = nome;
            return true;
        }

        return false;
    }

    @Override
    public void mover() {

    }

    @Override
    public void danoRecebido() {

    }
}
