package Jogo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ClassificacaoParaJSON {

    private String nomeMapa;
    private Jogador[] jogadores;
    private int numeroJogadores;

    public ClassificacaoParaJSON(String nomeMapa) {
        this.numeroJogadores = 0;
        this.nomeMapa = nomeMapa;
        this.jogadores = new Jogador[10];
    }

    public void lerClassificacaoJSON() throws IOException {
        Gson gson = new Gson();

        Reader reader = Files.newBufferedReader(Paths.get("./Classificacoes/" + this.nomeMapa + ".json"));

        ClassificacaoParaJSON classificacao = gson.fromJson(reader, ClassificacaoParaJSON.class);

        this.nomeMapa = classificacao.getNomeMapa();
        this.jogadores = classificacao.getJogadores();
        this.numeroJogadores = classificacao.getNumeroJogadores();

        System.out.println(this.toString());

        reader.close();
    }

    public void guardarClassificaoJSON() throws IOException {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        FileWriter writer = new FileWriter("./Classificacoes/" + this.nomeMapa + ".json");

        gson.toJson(this, writer);

        writer.flush();
        writer.close();
    }

    public void adicionarJogadores(Jogador jogador) {
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
}
