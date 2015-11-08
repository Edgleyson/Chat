package esse.chat.test;

import esse.chat.Cliente.FabricaDeInstituicao;
import esse.chat.modelo.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JpqlTest {

    private static EntityManagerFactory emf;
    private static final Logger logger = Logger.getGlobal();
    private EntityManager em;
    private EntityTransaction et;

    public JpqlTest() {
        Logger.getGlobal().setLevel(Level.INFO);
    }

    @BeforeClass
    public static void setUpClass() {
        logger.setLevel(Level.INFO);
        emf = Persistence.createEntityManagerFactory("ChatPU");
        DbUnitUtil.inserirDados();
    }

    @AfterClass
    public static void tearDownClass() {
        emf.close();
    }

    @Before
    public void setUp() {
        em = emf.createEntityManager();
        et = em.getTransaction();
        et.begin();
    }

    @After
    public void tearDown() {
        try {
            et.commit();
        } catch (Exception ex) {
            if (et.isActive()) {
                et.rollback();
            }

            if (ex.getCause() instanceof ConstraintViolationException) {
                ConstraintViolationException e = (ConstraintViolationException) ex.getCause();
                Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();

                for (ConstraintViolation violation : constraintViolations) {
                    Logger.getGlobal().log(Level.INFO, "{0}.{1}: {2}", new Object[]{violation.getRootBeanClass(), violation.getPropertyPath(), violation.getMessage()});
                }

                assertEquals(1, constraintViolations.size());
            }
        } finally {
            em.close();
            em = null;
            et = null;
        }
    }

    @Test //Usuario, Aluno, Email, Fone, Endereço
    public void t01_criarAlunoValido() {
        Email email = new Email();
        String str = "esa@a.recife.ifpe.edu.br";
        email.setEmail(str);

        Fone fone = new Fone();
        fone.setFone("(81)3321-7876");
        Fone fone2 = new Fone();
        fone2.setFone("(81)98876-4006");

        Aluno aluno = new Aluno();
        aluno.setPrimeiroNome("Edmilson");
        aluno.setUltimoNome("Araujo");
        aluno.setApelido("Edmilson");
        aluno.setCpf("045.189.874-54");
        aluno.setMatricula("20132Y6-RC0171");
        DateFormat f = DateFormat.getDateInstance();
        try {
            Date data = f.parse("03/12/1968");
            aluno.setNascimento(data);
        } catch (ParseException ex) {
            Logger.getLogger(FabricaDeInstituicao.class.getName()).log(Level.SEVERE, null, ex);
        }
        aluno.setSenha("Ed.71");
        aluno.setSexo("M");
        Endereco end = aluno.criarEndereco();
        end.setLogradouro("Rua Nova Dheli");
        end.setNumero("66");
        end.setComplemento("");
        end.setBairro("Setubal");
        end.setCep("50990-060");
        end.setCidade("Recife");
        end.setEstado("PE");

        aluno.adicionaEmail(email);
        aluno.adicionaFone(fone);
        aluno.adicionaFone(fone2);

        em.persist(aluno);
        assertNotNull(aluno.getId());
        assertNotNull(email.getId());
        assertNotNull(fone.getId());
        assertNotNull(fone2.getId());
    }

    @Test //Usuario/Aluno inválido
    public void t02_criarAlunoInvalido() throws ParseException {
        Aluno aluno = null;
        DateFormat f = DateFormat.getDateInstance();
        try {
            aluno = new Aluno();
            aluno.setPrimeiroNome("EDNELSON");//inválido
            aluno.setUltimoNome("araujo");//inválido
            aluno.setApelido("edinho");//apelido inválido
            aluno.setCpf("258.171.482-34"); //CPF inválido
            aluno.setMatricula("20132Y6-RC0162");
            Date data = f.parse("31/02/2016");
            aluno.setNascimento(data);//Data de nascimento inválida 
            aluno.setSenha("edde");//inválida
            aluno.setSexo("X"); //inválida
            em.persist(aluno);
            assertTrue(false);
        } catch (ConstraintViolationException ex) {
            Logger.getGlobal().info(ex.getMessage());

            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

            for (ConstraintViolation violation : constraintViolations) {
                Logger.getGlobal().log(Level.INFO, "{0}.{1}: {2}", new Object[]{violation.getRootBeanClass(), violation.getPropertyPath(), violation.getMessage()});
            }

            assertEquals(7, constraintViolations.size());
            assertNull(aluno.getId());
        }
    }

    @Test //Aluno válido com Endereço inválido
    public void t03_inserirEnderecoInvalido() throws ParseException {
        Aluno aluno = null;
        DateFormat f = DateFormat.getDateInstance();
        try {
            aluno = new Aluno();
            aluno.setPrimeiroNome("Anita");
            aluno.setUltimoNome("Schneider");
            aluno.setApelido("Ani");
            aluno.setCpf("124.012.452-00");
            aluno.setMatricula("20132Y6-RC0222");
            Date data = f.parse("20/09/1984");
            aluno.setNascimento(data);
            aluno.setSenha("Anit@1984");
            aluno.setSexo("F");
            Endereco end = aluno.criarEndereco();
            end.setLogradouro("Rua das Floristas");
            end.setNumero("19999399");//inválido
            end.setComplemento("");
            end.setBairro("Graças");
            end.setCep("50.560-020");//inválido
            end.setCidade("Recife");
            end.setEstado("PP");//inválido
            em.persist(aluno);
            assertTrue(false);
        } catch (ConstraintViolationException ex) {
            Logger.getGlobal().info(ex.getMessage());

            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

            for (ConstraintViolation violation : constraintViolations) {
                Logger.getGlobal().log(Level.INFO, "{0}.{1}: {2}", new Object[]{violation.getRootBeanClass(), violation.getPropertyPath(), violation.getMessage()});
            }

            assertEquals(3, constraintViolations.size());
            assertNull(aluno.getId());
        }
    }

    @Test //Aluno, Email
    public void t04_inserirEmailInvalido() {
        Logger.getGlobal().log(Level.INFO, "t04_inserirEmailInvalido");
            TypedQuery<Usuario> query = em.createQuery("SELECT a FROM Usuario a WHERE a.apelido like :apelido", Usuario.class);
            query.setParameter("apelido", "Edmilson");
            Usuario usuario = query.getSingleResult();
            Email email = new Email();
            email.setEmail("ema.recife.ifpe.edu.br");//inválido
            usuario.adicionaEmail(email);
    }

    @Test //Aluno, Email
    public void t05_inserirEmailValido() {
        Logger.getGlobal().log(Level.INFO, "t05_inserirEmailInvalido");
        TypedQuery<Usuario> query = em.createQuery("SELECT a FROM Usuario a WHERE a.apelido like :apelido", Usuario.class);
        query.setParameter("apelido", "Edmilson");
        Usuario usuario = query.getSingleResult();
        Email email = new Email();
        email.setEmail("ema@recife.ifpe.edu.br");
        usuario.adicionaEmail(email);
        em.merge(usuario);
        query = em.createQuery("SELECT a FROM Usuario a WHERE a.apelido like :apelido", Usuario.class);
        query.setParameter("apelido", "Edmilson");
        usuario = query.getSingleResult();
        assertEquals(2, usuario.getEmails().size());
    }

    @Test //Instituição, Email, Fone, Endereço
    public void t07_criarInstituicaoValida() {
        Email email = new Email();
        String str = "fso@fso.com.br";
        email.setEmail(str);

        Fone fone = new Fone();
        fone.setFone("(81)3031-1135");

        Instituicao instituicao = new Instituicao();
        instituicao.setNome("Faculdade Salgado de Oliveira");
        instituicao.setSigla("FSO");
        instituicao.setCnpj("12.493.133/0001-26");

        Endereco end = instituicao.criarEndereco();
        end.setLogradouro("Av Caxanga");
        end.setNumero("428");
        end.setComplemento("");
        end.setBairro("Madalena");
        end.setCep("50610-230");
        end.setCidade("Recife");
        end.setEstado("PE");

        instituicao.adicionaEmail(email);
        instituicao.adicionaFone(fone);

        em.persist(instituicao);
        assertNotNull(instituicao.getId());
        assertNotNull(email.getId());
        assertNotNull(fone.getId());

    }

    @Test //Instituição
    public void t06_criarInstituicaoInvalida() {
        Instituicao instituicao = null;
        try {
            instituicao = new Instituicao();
            instituicao.setNome("");//Invalido
            instituicao.setSigla("Faculdade Salgado de Oliveira");//Invalido
            instituicao.setCnpj("12.493.133/0001-28");//Invalido
            em.persist(instituicao);
            assertNotNull(instituicao.getId());
        } catch (ConstraintViolationException ex) {
            Logger.getGlobal().info(ex.getMessage());

            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

            for (ConstraintViolation violation : constraintViolations) {
                Logger.getGlobal().log(Level.INFO, "{0}.{1}: {2}", new Object[]{violation.getRootBeanClass(), violation.getPropertyPath(), violation.getMessage()});
            }
            assertEquals(3, constraintViolations.size());
            assertNull(instituicao.getId());
        }

    }

    @Test //Instituição, Fone
    public void t08_inserirFoneValido() {
        Logger.getGlobal().log(Level.INFO, "t08_inserirFonevalido");
        TypedQuery<Instituicao> query = em.createQuery("SELECT i FROM Instituicao i WHERE i.sigla like :sigla", Instituicao.class);
        query.setParameter("sigla", "FSO");
        Instituicao instituicao = query.getSingleResult();
        Fone fone = new Fone();
        fone.setFone("(81)3088-4330");
        instituicao.adicionaFone(fone);
        em.merge(instituicao);
        query = em.createQuery("SELECT i FROM Instituicao i WHERE i.sigla like :sigla", Instituicao.class);
        query.setParameter("sigla", "FSO");
        instituicao = query.getSingleResult();
        assertEquals(2, instituicao.getFones().size());
    }

    @Test //Instituição, Fone
    public void t09_inserirFoneInvalido() {
        Logger.getGlobal().log(Level.INFO, "t09_inserirFoneInvalido");
        TypedQuery<Instituicao> query = em.createQuery("SELECT i FROM Instituicao i WHERE i.sigla like :sigla", Instituicao.class);
        query.setParameter("sigla", "FSO");
        Instituicao instituicao = query.getSingleResult();
        Fone fone = new Fone();
        fone.setFone("(01)3031-1135");// invalido
        instituicao.adicionaFone(fone);

    }

    @Test //Instituição, Curso
    public void t10_inserirCursoInvalido() {
        Logger.getGlobal().log(Level.INFO, "t10_inserirCursoInvalido");
        TypedQuery<Instituicao> query = em.createQuery("SELECT i FROM Instituicao i WHERE i.sigla like :sigla", Instituicao.class);
        query.setParameter("sigla", "FSO");
        Instituicao instituicao = query.getSingleResult();
        Curso cursoSuperior = new Curso();
        instituicao.adicionaCurso(cursoSuperior);//curso inválido - null
    }

    @Test //Instituição, Curso
    public void t11_inserirCursoValido() {
        Logger.getGlobal().log(Level.INFO, "t11_inserirCursoValido");
        TypedQuery<Instituicao> query = em.createQuery("SELECT i FROM Instituicao i WHERE i.sigla like :sigla", Instituicao.class);
        query.setParameter("sigla", "FSO");
        Instituicao instituicao = query.getSingleResult();
        Curso cursoSuperior = new Curso();
        cursoSuperior.setNome("Análise de Sistemas");
        instituicao.adicionaCurso(cursoSuperior);
        em.merge(instituicao);
        query = em.createQuery("SELECT i FROM Instituicao i WHERE i.sigla like :sigla", Instituicao.class);
        query.setParameter("sigla", "FSO");
        instituicao = query.getSingleResult();
        assertEquals(1, instituicao.getCursos().size());
    }

    @Test //Curso, Disciplina
    public void t12_inserirDisciplinaInvalida() {
        Logger.getGlobal().log(Level.INFO, "t10_inserirCursoInvalido");
        TypedQuery<Curso> query = em.createQuery("SELECT c FROM Curso c WHERE c.instituicao.sigla = ?1 and c.nome = ?2", Curso.class);
        query.setParameter(1, "FSO");
        query.setParameter(2, "Análise de Sistemas");
        Curso cursoSuperior = query.getSingleResult();
        Disciplina disciplina = new Disciplina();
        cursoSuperior.adicionaDisciplina(disciplina);//Disciplina inválida - null
    }

    @Test //Curso, Disciplina, Instituição
    public void t13_inserirDisciplinaValida() {
        Logger.getGlobal().log(Level.INFO, "t13_inserirDisciplinaValida");
        TypedQuery<Curso> query = em.createQuery("SELECT c FROM Curso c WHERE c.instituicao.sigla = ?1 and c.nome = ?2", Curso.class);
        query.setParameter(1, "FSO");
        query.setParameter(2, "Análise de Sistemas");
        Curso cursoSuperior = query.getSingleResult();

        TypedQuery<Professor> query2 = em.createQuery("SELECT p FROM Professor p WHERE p.cpf = ?1", Professor.class);
        query2.setParameter(1, "113.795.963-00");
        Professor professor = query2.getSingleResult();
        Disciplina d1 = new Disciplina();
        d1.setNome("Iniciação à Informática");
        d1.setProfessor(professor);
        Disciplina d2 = new Disciplina();
        d2.setNome("Lógica de Programação");
        d2.setProfessor(professor);

        query2.setParameter(1, "539.461.536-58");
        Professor professor2 = query2.getSingleResult();
        Disciplina d3 = new Disciplina();
        d3.setNome("Inglês I");
        d3.setProfessor(professor2);

        query2.setParameter(1, "984.447.123-02");
        Professor professor3 = query2.getSingleResult();
        Disciplina d4 = new Disciplina();
        d4.setNome("Iniciação à Programação");
        d4.setProfessor(professor3);
        Disciplina d5 = new Disciplina();
        d5.setNome("Ética");
        d5.setProfessor(professor3);
        Collection<Disciplina> disciplinas = new ArrayList<>();
        disciplinas.add(d1);
        disciplinas.add(d2);
        disciplinas.add(d3);
        disciplinas.add(d4);
        disciplinas.add(d5);
        cursoSuperior.setDisciplinas(disciplinas);
        query = em.createQuery("SELECT c FROM Curso c WHERE c.instituicao.sigla = ?1 and c.nome = ?2", Curso.class);
        query.setParameter(1, "FSO");
        query.setParameter(2, "Análise de Sistemas");
        cursoSuperior = query.getSingleResult();
        assertEquals(5, cursoSuperior.getDisciplinas().size());
    }

    @Test //Aluno, 
    public void t14_update() {
        logger.info("Executando t14: UPDATE Aluno AS a SET a.apelido = ?1 WHERE a.id = ?2");
        Long id = (long) 3;
        Query query = em.createQuery("UPDATE Aluno AS a SET a.apelido = ?1 WHERE a.id = ?2");
        query.setParameter(1, "Edde1968");
        query.setParameter(2, id);
        query.executeUpdate();
        em.clear();
        Aluno aluno = em.find(Aluno.class, id);
        assertEquals("Edde1968", aluno.getApelido());
        logger.info(aluno.getApelido());
    }

    @Test//
    public void t15_delete() {
        Long id = (long) 29;
        logger.info("Executando t15: DELETE Fone AS f WHERE f.id = ?1");
        Query query = em.createQuery("DELETE Fone AS f WHERE f.id = ?1");
        query.setParameter(1, id);
        query.executeUpdate();
        Fone fone = em.find(Fone.class, id);
        assertNull(fone);
        logger.log(Level.INFO, "Fone {0} removido com sucesso.", id);
    }

}
