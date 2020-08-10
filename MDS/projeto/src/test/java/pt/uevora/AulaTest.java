package pt.uevora;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class AulaTest {
    Aula aula;

    @Before
    public void setUp() throws Exception {
        aula = new Aula(1, "2020-06-01", "10:10");
    }

    @After
    public void tearDown() throws Exception {
        aula = null;
    }

    @Test
    public void regista_presencaTest() {
        int cartao = 42;
        int qtdTest = aula.get_qtd_presencas()+1;
        aula.regista_presenca(42);
        assertEquals(1, aula.presentes[cartao]);
        assertEquals(qtdTest, aula.get_qtd_presencas());
    }

    @Test
    public void presencasTest() throws IOException {
        aula.presencas("src/test/resources/fileTest.json");
        assertEquals(5, aula.get_qtd_presencas());
        assertEquals(1, aula.presentes[100]);
        assertEquals(0, aula.presentes[80]);

    }

    @Test
    public void get_qtd_presencasTest() throws IOException {
        aula.presencas("src/test/resources/fileTest.json");
        assertEquals(5, aula.get_qtd_presencas());
    }
}