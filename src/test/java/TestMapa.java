import Estruturas.InvalidIndexException;
import Exceptions.MapaException;
import Jogo.Mapa;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestMapa {

    Mapa m;

    @Test
    public void testCase01() {
        m = new Mapa();
        m.lerJson("mapa.json");
        assertFalse(m.temEntradaOuExterior());
    }

    @Test
    public void testCase02() {
        m = new Mapa();
        m.lerJson("mapa.json");
        assertTrue(m.temLigacaoEntrada());
    }

    @Test
    public void testCase03(){
        m = new Mapa();
        m.lerJson("mapa.json");
        assertTrue(m.temLigacaoExterior());
    }

    @Test
    public void testCase04() {
        m = new Mapa();
        m.lerJson("mapaErrado2.json");
        assertFalse(m.temLigacaoExterior());
    }

    @Test
    public void testCase05() {
        m = new Mapa();
        m.lerJson("mapaErrado1.json");
        assertFalse(m.temLigacaoEntrada());
    }

    @Test
    public void testCase06() {
        m = new Mapa();
        m.lerJson("mapaErrado3.json");
        assertTrue(m.temEntradaOuExterior());
    }

    @Test
    public void testCase07() {
        m = new Mapa();
        m.lerJson("mapaErrado4.json");
        assertTrue(m.temEntradaOuExterior());
    }

}
