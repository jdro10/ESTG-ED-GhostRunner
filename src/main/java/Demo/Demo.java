package Demo;

import Estruturas.InvalidIndexException;
import Exceptions.MapaException;
import Jogo.ClassificacaoParaJSON;
import Jogo.Jogador;

import java.io.IOException;

public class Demo {

    public static void main(String[] args) throws InvalidIndexException, MapaException, IOException {

        Jogador jogador = new Jogador("Jorge2");
        Jogador jogador2 = new Jogador("Jorge3");

        ClassificacaoParaJSON json = new ClassificacaoParaJSON("Teste");
        json.adicionarJogadores(jogador);
        json.adicionarJogadores(jogador2);
        json.guardarClassificaoJSON();




    }

}
