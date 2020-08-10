package pt.uevora;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UtilizadorTest {

    Utilizador utilizador;

    @Before
    public void setUp() throws Exception {
        utilizador = new Utilizador(42, "Promessas", "docente");
    }

    @After
    public void tearDown() throws Exception {
        utilizador = null;
    }

    @Test
    public void registar_faltaTest() {
        int num_aula = 5;
        utilizador.registar_falta(num_aula);
        assertEquals(0, utilizador.presencas[num_aula]);
    }

    @Test
    public void registar_presencaTest() {
        int num_aula = 5;
        utilizador.registar_presenca(num_aula);
        assertEquals(1, utilizador.presencas[num_aula]);
    }

    @Test
    public void justificar_faltaTest() {
        int num_aula = 5;
        utilizador.justificar_falta(num_aula);
        assertEquals(2, utilizador.presencas[num_aula]);
    }

    @Test
    public void consultar_faltasTest() {
        int num_aula = 4;
        utilizador.presencas = new int[]{0, 1, 0, 1, 0, 1, 0, 1, 0, 1};
        assertEquals("F P F P ", utilizador.consultar_faltas(4));
    }

    @Test
    public void inc_faltasTest(){
        int a = utilizador.get_qtd_faltas()+1;
        utilizador.inc_faltas();
        assertEquals(a, utilizador.get_qtd_faltas());
    }


    @Test
    public void get_cartaoTest(){
        assertEquals(42, utilizador.get_cartao());
    }

    @Test
    public void get_passwordTest(){
        assertEquals("password", utilizador.get_password());
    }

    @Test
    public void get_nomeTest(){
        assertEquals("Promessas", utilizador.get_nome());
    }

    @Test
    public void get_papelTest(){
        assertEquals("docente", utilizador.get_papel());
    }
    @Test
    public void get_qtd_faltasTest(){
        int a = utilizador.get_qtd_faltas();
        utilizador.inc_faltas();

        assertEquals(a+1,utilizador.get_qtd_faltas());
    }

}