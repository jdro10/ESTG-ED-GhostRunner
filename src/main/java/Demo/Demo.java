package Demo;

import Jogo.Mapa;

public class Demo {

    public static void main(String[] args){

        Mapa mapa = new Mapa();

        mapa.lerJson();

        System.out.println(mapa.toString());

        System.out.println(mapa.numeroSalas());
    }
}
