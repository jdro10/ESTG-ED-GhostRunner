package Jogo;

public class Aposento {

    private String aposento;
    private int fantasma;
    private String[] ligacoes;

    public Aposento(String aposento, int fantasma, String[] ligacoes) {
        this.aposento = aposento;
        this.fantasma = fantasma;
        this.ligacoes = ligacoes;
    }

    public String getAposento() {
        return aposento;
    }

    public int getFantasma() {
        return fantasma;
    }

    public String getLigacoes() {
        String s = "";

        if (this.ligacoes == null) {
            return "";
        }

        for (int i = 0; i < this.ligacoes.length; i++) {
            if (i == this.ligacoes.length - 1) {
                s += this.ligacoes[i];
            } else {
                s += this.ligacoes[i] + ",";
            }
        }

        return s;
    }

    @Override
    public String toString() {
        return "Aposento{" +
                "Nome='" + this.aposento + '\'' +
                ", Numero de fantasmas=" + this.fantasma +
                '}';
    }
}