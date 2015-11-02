package esse.chat.test;


import esse.chat.Cliente.FabricaDeInstituicao;
import esse.chat.modelo.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
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

    
    @Test //Usuario, Aluno, Endereco, Fone, Email
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
    @Test //Usuario, Aluno, Endereco, Fone, Email
    public void t02_criarAlunoInvalido() throws ParseException {
        Aluno aluno = null;
        DateFormat f = DateFormat.getDateInstance();
        
        Email email = new Email();
        String str = "ema.recife.ifpe.edu.br";//inválido
        email.setEmail(str);
        
        Fone fone = new Fone();
        fone.setFone("(81)9.8821.7866");//inválido
        
        try {
            aluno = new Aluno();
            aluno.setPrimeiroNome("EDNELSON");//inválido
            aluno.setUltimoNome("araujo");//inválido
            aluno.setApelido("EddeMenezes");//apelido inválido
            aluno.setCpf("258.171.482-34"); //CPF inválido
            aluno.setMatricula("20132Y6-RC0162");
            Date data = f.parse("31/02/2016");
            aluno.setNascimento(data);//Data de nascimento inválida 
            aluno.setSenha("edde");//inválida
            aluno.setSexo("M");
            Endereco end = aluno.criarEndereco();
            end.setLogradouro("Rua Jose Ponciano");
            end.setNumero("199960");//inválido
            end.setComplemento("");
            end.setBairro("Jardim São Paulo");
            end.setCep("50.910-060");//inválido
            end.setCidade("Recife");
            end.setEstado("Pernambuco");//inválido
            aluno.adicionaEmail(email);
            aluno.adicionaFone(fone);   
                        
            em.persist(aluno);
            assertTrue(false);
        } catch (ConstraintViolationException ex) {
            Logger.getGlobal().info(ex.getMessage());

            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

            for (ConstraintViolation violation : constraintViolations) {
                Logger.getGlobal().log(Level.INFO, "{0}.{1}: {2}", new Object[]{violation.getRootBeanClass(), violation.getPropertyPath(), violation.getMessage()});
            }

            assertEquals(4, constraintViolations.size());
            assertNull(aluno.getId());
            assertNull(email.getId());
            assertNull(fone.getId());
        }
    }

    
}
