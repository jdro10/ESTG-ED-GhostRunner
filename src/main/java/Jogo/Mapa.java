package Jogo;

import Estruturas.ArrayOrderedList;
import Estruturas.NoComparableException;
import com.google.gson.Gson;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Mapa {

    public String nome;
    public int pontos;
    public Aposento[] mapa;
    private ArrayOrderedList<Classificacao> arrayOrderedList;

    public Mapa() {
        this.arrayOrderedList = new ArrayOrderedList<>();
    }

    public void lerJson(String s) {
        try {
            Gson gson = new Gson();

            String json = "./mapExample/" + s;

            Reader reader = Files.newBufferedReader(Paths.get(json));

            Mapa mapa = gson.fromJson(reader, Mapa.class);

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

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public Aposento[] getMapa() {
        return mapa;
    }

    public void setMapa(Aposento[] mapa) {
        this.mapa = mapa;
    }

    public int numeroSalas() {
        return mapa.length;
    }

    public Aposento getAposento(int i){
        return this.mapa[i];
    }

    public void ordenaClassificacaoMapa(Classificacao classificacao) throws NoComparableException {
        this.arrayOrderedList.add(classificacao);
    }
  
    @Override
    public String toString() {
        return "Jogo.Mapa{" +
                "nome='" + nome + '\'' +
                this.arrayOrderedList.toString() +
                '}';
    }

    public boolean temEntradaOuExterior(){
        for(Aposento aposento : mapa){
            if(aposento.getAposento().equals("entrada") || aposento.getAposento().equals("exterior")){
                return true;
            }
        }

        return false;
    }

    public boolean temLigacaoEntrada(){
        for(Aposento aposento : mapa){
            if(aposento.getLigacoes().contains("entrada")){
                return true;
            }
        }

        return false;
    }

    public boolean temLigacaoExterior(){
        for(Aposento aposento : mapa){
            if(aposento.getLigacoes().contains("exterior")){
                return true;
            }
        }

        return false;
    }
}
