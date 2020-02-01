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


}
