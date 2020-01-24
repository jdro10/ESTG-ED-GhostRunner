package Jogo;

import com.google.gson.Gson;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Mapa {

    public String nome;
    public String pontos;
    public Aposento[] mapa;

    public void lerJson(){
        try {
            Gson gson = new Gson();

            String json = "./mapExample/mapa.json";

            Reader reader = Files.newBufferedReader(Paths.get(json));

            Mapa mapa = gson.fromJson(reader,Mapa.class);

            this.setMapa(mapa.getMapa());
            this.setNome(mapa.getNome());
            this.setPontos(mapa.getPontos());

            reader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPontos() {
        return pontos;
    }

    public void setPontos(String pontos) {
        this.pontos = pontos;
    }

    public Aposento[] getMapa() {
        return mapa;
    }

    public void setMapa(Aposento[] mapa) {
        this.mapa = mapa;
    }

    public int numeroSalas(){
        return mapa.length;
    }

    @Override
    public String toString() {
        return "Jogo.Mapa{" +
                "nome='" + nome + '\'' +
                ", pontos=" + pontos +
                ", mapa=" + Arrays.toString(mapa) +
                '}';
    }
}
