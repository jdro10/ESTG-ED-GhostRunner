import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Demo {

    public static void main(String[] args) throws FileNotFoundException {

        Mapa mapa = new Mapa();

        mapa.lerJson();

        System.out.println(mapa.toString());

    }
}
