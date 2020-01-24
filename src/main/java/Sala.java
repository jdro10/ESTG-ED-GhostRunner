import java.util.Arrays;

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

    public String getLigacoes() {
        String s = "";

        for(int i = 0 ; i < this.ligacoes.length ; i++){
            if(i == this.ligacoes.length-1){
                s += this.ligacoes[i];
            }else{
                s += this.ligacoes[i] + ",";
            }
        }

        return s;
    }

    public void setLigacoes(String[] ligacoes) {
        this.ligacoes = ligacoes;
    }

    @Override
    public String toString() {
        return "Sala{" +
                "aposento='" + aposento + '\'' +
                ", fantasma=" + fantasma +
                ", ligacoes=" + getLigacoes() +
                '}';
    }

}
