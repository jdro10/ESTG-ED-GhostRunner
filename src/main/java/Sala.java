public class Sala {

    private String aposento;
    private int fantasma;
    private String [] ligacoes;


    public Sala(String aposento, int fantasma, String[] ligacoes) {
        this.aposento = aposento;
        this.fantasma = fantasma;
        this.ligacoes = ligacoes;
    }

    public String getAposento() {
        return aposento;
    }

    public void setAposento(String aposento) {
        this.aposento = aposento;
    }

    public int getFantasma() {
        return fantasma;
    }

    public void setFantasma(int fantasma) {
        this.fantasma = fantasma;
    }

    public String[] getLigacoes() {
        return ligacoes;
    }

    public void setLigacoes(String[] ligacoes) {
        this.ligacoes = ligacoes;
    }
}
