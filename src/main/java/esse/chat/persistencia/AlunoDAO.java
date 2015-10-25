package esse.chat.persistencia;

import esse.chat.modelo.Aluno;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class AlunoDAO {
    
    public AlunoDAO() {
	}
	
	public static Long inserirAluno(Aluno aluno) throws Exception {
            EntityManager em = null;
            EntityTransaction et = null;
            try{
		em = FabricaDAOJPA.getInstance().getFactory().createEntityManager();
		et = em.getTransaction();
		et.begin();
		em.persist(aluno);
		et.commit();
            } catch (Exception ex) {
                if (et != null) {
                if (et.isActive()) {
                    Logger.getGlobal().log(Level.SEVERE,
                            "Erro! Cancelando transação. Mensagem: {0}", ex.getMessage());
                    et.rollback();
                    Logger.getGlobal().info("Transação cancelada");                
                }
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return aluno.getId();
	}

	@SuppressWarnings("unchecked")
	public static List<Aluno> getList(String condicao) throws Exception {
		EntityManager em = FabricaDAOJPA.getInstance().getFactory().createEntityManager();
		
		List<Aluno> alunos = em.createQuery(condicao).getResultList();
		em.close();
		return alunos;
	}

	public static Aluno pesquisarAluno(Long id) throws Exception {
		EntityManager em = FabricaDAOJPA.getInstance().getFactory().createEntityManager();
		Aluno user = (Aluno) em.find(Aluno.class, id);
		em.close();
		return user;
	}

	public static void deletarAluno(Aluno aluno) throws Exception {
		EntityManager em = FabricaDAOJPA.getInstance().getFactory().createEntityManager();
		em.getTransaction().begin();
		Aluno user = (Aluno) em.find(Aluno.class, aluno.getId());
		em.remove(user);
		em.getTransaction().commit();
		em.close();
	}

	public static void atualizarAluno(Aluno aluno) throws Exception {
		EntityManager em = FabricaDAOJPA.getInstance().getFactory().createEntityManager();
		em.getTransaction().begin();
		em.merge(aluno);
		em.getTransaction().commit();
		em.close();
        }
}
