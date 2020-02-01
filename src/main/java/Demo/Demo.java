package Demo;

import Estruturas.InvalidIndexException;
import Jogo.Menu;

public class Demo {

    public static void main(String[] args) {

        Menu menu = new Menu();

        try {
            menu.menuPrincipal();
        } catch (InvalidIndexException e) {
            System.out.println(e);
        }

        /*
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
        */

    }

}
