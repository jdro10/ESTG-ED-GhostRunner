package Jogo;

import Estruturas.ArrayUnorderedList;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Classificacoes {

    private ArrayUnorderedList<Classificacao> classificacoes;
    private final String json = "./classificacoes/classificacoes.json";
    private Gson gson;

    public void adicionaClassificacao(Classificacao classificacaoParaAdicionar){
        classificacoes.addToFront(classificacaoParaAdicionar);
        this.gson = new Gson();
    }

    public void guardarParaFicheiro() throws IOException {
        gson.toJson(classificacoes,new FileWriter(json));
    }

    public void ler() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(json));

        Classificacoes cl = gson.fromJson(reader, Classificacoes.class);

        reader.close();
    }
}
