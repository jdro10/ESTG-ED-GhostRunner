package Demo;

import Estruturas.InvalidIndexException;
import Estruturas.Network;
import Estruturas.NoComparableException;
import Jogo.Jogo;
import Jogo.Mapa;
import Enum.Dificuldade;

public class Demo {

    public static void main(String[] args) throws NoComparableException, InvalidIndexException {

        Mapa mapa = new Mapa();

        mapa.lerJson();

        Jogo j = new Jogo(mapa,"xPromate", Dificuldade.FACIL);

        //System.out.println(j.getGraph().toString());

        j.mostrarOpcoes(0);
        System.out.println("------------------------");
        j.mostrarOpcoes(2);
        System.out.println("------------------------");
        j.mostrarOpcoes(2);
        System.out.println("------------------------");
        j.mostrarOpcoes(2);
        System.out.println("------------------------");
        j.mostrarOpcoes(99999999);

        //foi até ao exterior
        //ainda não tira pontuações mas isso penso q vai ser básico






        /**
        Network<Integer> network = new Network<>();
        network.addVertex(0);
        network.addVertex(1);
        network.addVertex(2);
        network.addVertex(3);
        network.addVertex(4);


        network.addEdge(0, 1, 0);
        network.addEdge(1, 2, 3);
        network.addEdge(2, 3, 1);
        network.addEdge(3, 4, 3);
        network.addEdge(1, 4, 2);
        network.addEdge(0, 3, 0);

        network.dijkstraShortestPath(0);

        System.out.println("\n");

        /*
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
