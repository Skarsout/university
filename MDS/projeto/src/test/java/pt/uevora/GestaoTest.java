package pt.uevora;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GestaoTest {
    Gestao gestao;
    Aula aulaTest;
    Utilizador userTest;

    @Before
    public void setUp() throws Exception {
        gestao = new Gestao();
        userTest = new Utilizador(42, "Promessas", "docente");
        aulaTest = new Aula(1,"2020-06-01", "10:10");
    }

    @After
    public void tearDown() throws Exception {
        gestao = null;
    }

    @Test
    public void nova_aulaTest() {
        gestao.nova_aula("2020-06-01", "10:10");
        assertEquals(aulaTest.data, gestao.aulas[0].data);
    }

    @Test
    public void novo_utilizadorTest() {
        gestao.novo_utilizador(42, "Promessas", "docente");
        assertEquals(userTest.get_nome(), gestao.inscritos[42].get_nome());
    }

    @Test
    public void consultar_faltas_por_alunoTest() {
        gestao.aula_atual = 10;
        gestao.novo_utilizador(42, "Promessas", "docente");
        gestao.inscritos[42].presencas = new int[]{0, 1, 0, 1, 0, 1, 0, 1, 0, 1};
        assertEquals("F P F P F P F P F P ", gestao.consultar_faltas_por_aluno(42));
    }

    @Test
    public void inc_aula_atualTest() {
        gestao.aula_atual = 6;
        gestao.inc_aula_atual();
        assertEquals(7, gestao.aula_atual);
    }

    @Test
    public void userReaderTest() {
    }

    @Test
    public void aulaReaderTest() {
    }

    @Test
    public void menuTest() {
    }

    @Test
    public void loginTest() {
    }
}