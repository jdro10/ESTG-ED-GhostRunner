package Demo;

import Estruturas.InvalidIndexException;
import Estruturas.Network;
import Estruturas.NoComparableException;
import Jogo.Jogo;
import Jogo.Mapa;
import Enum.Dificuldade;

public class Demo {

    public static void main(String[] args) throws InvalidIndexException {

        Mapa mapa = new Mapa();

        mapa.lerJson();

        Jogo j = new Jogo(mapa,"xPromate", Dificuldade.FACIL);

        System.out.println(j.getGraph().toString());
    }
}
