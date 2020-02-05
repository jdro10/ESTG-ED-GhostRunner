package Jogo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import Enum.Dificuldade;

public class Classificacao {

    private String nomeMapa;
    private Jogador[] jogadores;
    private final int DEFAULT_CAPACITY = 5;
    private int numeroJogadores;
    private Dificuldade dificuldade;

    public Classificacao(String nomeMapa, Dificuldade dificuldade) {
        this.numeroJogadores = 0;
        this.nomeMapa = nomeMapa;
        this.dificuldade = dificuldade;
        this.jogadores = new Jogador[DEFAULT_CAPACITY];
    }

    public Classificacao(String nomeMapa) {
        this.numeroJogadores = 0;
        this.nomeMapa = nomeMapa;
        this.jogadores = new Jogador[DEFAULT_CAPACITY];
    }

    public void carregarClassificacaoJSON() throws IOException {
        Gson gson = new Gson();

        Reader reader = Files.newBufferedReader(Paths.get("./Classificacoes/" + this.nomeMapa + this.dificuldade + ".json"));

        Classificacao classificacao = gson.fromJson(reader, Classificacao.class);

        this.nomeMapa = classificacao.getNomeMapa();
        this.jogadores = classificacao.getJogadores();
        this.numeroJogadores = classificacao.getNumeroJogadores();
        this.dificuldade = classificacao.getDificuldade();

        reader.close();
    }

    public void lerClassificacaoJSON() throws IOException {
        Gson gson = new Gson();

        Reader reader = Files.newBufferedReader(Paths.get("./Classificacoes/" + this.nomeMapa + ".json"));

        Classificacao classificacao = gson.fromJson(reader, Classificacao.class);

        this.nomeMapa = classificacao.getNomeMapa();
        this.jogadores = classificacao.getJogadores();
        this.numeroJogadores = classificacao.getNumeroJogadores();
        this.dificuldade = classificacao.getDificuldade();

        reader.close();
    }

    public void guardarClassificaoJSON() throws IOException {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        FileWriter writer = new FileWriter("./Classificacoes/" + this.nomeMapa + this.dificuldade + ".json");

        gson.toJson(this, writer);

        writer.flush();
        writer.close();
    }

    public void adicionarJogadores(Jogador jogador) {
        if (this.jogadores.length == this.numeroJogadores) {
            expandCapacity();
        }

        this.jogadores[this.numeroJogadores] = jogador;
        this.numeroJogadores++;
    }

    public Jogador[] getJogadores() {
        return jogadores;
    }

    public String getNomeMapa() {
        return nomeMapa;
    }

    public int getNumeroJogadores() {
        return numeroJogadores;
    }

    public Jogador getJogador(int index) {
        return this.jogadores[index];
    }

    public Dificuldade getDificuldade() {
        return dificuldade;
    }

    private void expandCapacity() {
        Jogador[] novoArray = new Jogador[this.jogadores.length * 2];

        for (int i = 0; i < this.jogadores.length; i++) {
            novoArray[i] = this.jogadores[i];
        }

        this.jogadores = novoArray;
    }
}
