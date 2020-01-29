package Demo;

import Estruturas.NoComparableException;
import Jogo.Jogo;
import Jogo.Mapa;
import Jogo.Classificacao;
import Jogo.Jogador;

public class Demo {

    public static void main(String[] args) throws NoComparableException {


        Mapa mapa = new Mapa();

        mapa.lerJson();

        Jogo j = new Jogo(mapa);

        System.out.println(j.getGraph().toString());


        /**
        Jogador j = new Jogador("xPromate");
        Classificacao cl = new Classificacao("mapaFixe",j);
        Classificacao cl2 = new Classificacao("mapaFixe",j);
        Classificacao cl3 = new Classificacao("mapaFixe",j);

        Mapa mapa = new Mapa();

        mapa.lerJson();

        mapa.ordenaClassificacaoMapa(cl);
        mapa.ordenaClassificacaoMapa(cl2);
        mapa.ordenaClassificacaoMapa(cl3);


        System.out.println(mapa.toString());*/

    }
}
