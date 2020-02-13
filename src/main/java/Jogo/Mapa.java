package Jogo;

import com.google.gson.Gson;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Mapa {

    public String nome;
    public int pontos;
    public Aposento[] mapa;

    /**
     * método responsável por carregar o ficheiro json do mapa, ou seja, pontuação, nome e aposentos
     * @param s
     */
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

    public Aposento getAposento(int i) {
        return this.mapa[i];
    }


    public boolean temEntradaOuExterior() {
        for (Aposento aposento : mapa) {
            if (aposento.getAposento().equals("entrada") || aposento.getAposento().equals("exterior")) {
                return true;
            }
        }

        return false;
    }

    public boolean temLigacaoEntrada() {
        for (Aposento aposento : mapa) {
            if (aposento.getLigacoes().contains("entrada")) {
                return true;
            }
        }

        return false;
    }

    public boolean temLigacaoExterior() {
        for (Aposento aposento : mapa) {
            if (aposento.getLigacoes().contains("exterior")) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        String s = "Nome:" + this.nome + "\n" +"Pontos: " + this.pontos + "\n";
        s +="Aposento{Nome='entrada', Numero de fantasmas=0}\n";
        for (Aposento ap : this.mapa){
            s += ap.toString() + "\n";
        }
        s +="Aposento{Nome='exterior', Numero de fantasmas=0}\n";
        return s;
    }
}
