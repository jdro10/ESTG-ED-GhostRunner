package Demo;

import Estruturas.InvalidIndexException;
import Estruturas.NoComparableException;
import Exceptions.MapaException;
import Jogo.Menu;

import java.io.IOException;

public class Demo {

    public static void main(String[] args) {

        Menu menu = new Menu();

        try {
            menu.menuPrincipal();
        } catch (InvalidIndexException | MapaException | NoComparableException | IOException e) {
            System.out.println(e);
        }
    }

}
