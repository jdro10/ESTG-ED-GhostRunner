package Demo;

import Estruturas.InvalidIndexException;
import Jogo.Jogo;
import Jogo.Menu;
import Jogo.Mapa;
import Enum.Dificuldade;

public class Demo {

    public static void main(String[] args) throws InvalidIndexException {

        /*
        Menu menu = new Menu();

        try {
            menu.menuPrincipal();
        } catch (InvalidIndexException e) {
            System.out.println(e);
        }*/

        Mapa m = new Mapa();
        m.lerJson();

        Jogo j = new Jogo(m, Dificuldade.BASICO);


        j.mostrarHipoteses();
        System.out.println(j.getLocalJogador());
        j.escolheOpcoes(0);
        System.out.println("------------------------");

        j.mostrarHipoteses();
        System.out.println(j.getLocalJogador());
        j.escolheOpcoes(0);
        System.out.println("------------------------");

        j.mostrarHipoteses();
        System.out.println(j.getLocalJogador());
        //j.escolheOpcoes();
        System.out.println("------------------------");

        j.mostrarHipoteses();

        //foi até ao exterior
        //ainda não tira pontuações mas isso penso q vai ser básico


    }

}
