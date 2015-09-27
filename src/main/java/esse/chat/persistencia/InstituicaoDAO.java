package esse.chat.persistencia;

import esse.chat.modelo.Instituicao;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class InstituicaoDAO {
    
    public InstituicaoDAO() {
	}
	
	public static Long inserirInstituicao(Instituicao instituicao) throws Exception {
            EntityManager em = null;
            EntityTransaction et = null;
            try{
		em = FabricaDAOJPA.getInstance().getFactory().createEntityManager();
		et = em.getTransaction();
		et.begin();
		em.persist(instituicao);
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
        return instituicao.getId();
	}

	@SuppressWarnings("unchecked")
	public static List<Instituicao> getList(String condicao) throws Exception {
		EntityManager em = FabricaDAOJPA.getInstance().getFactory().createEntityManager();
		
		List<Instituicao> usuarios = em.createQuery(condicao).getResultList();
		em.close();
		return usuarios;
	}

	public static Instituicao pesquisarInstituicao(Long id) throws Exception {
		EntityManager em = FabricaDAOJPA.getInstance().getFactory().createEntityManager();
		Instituicao ins = (Instituicao) em.find(Instituicao.class, id);
		em.close();
		return ins;
	}

	public static void deletarInstituicao(Instituicao instituicao) throws Exception {
		EntityManager em = FabricaDAOJPA.getInstance().getFactory().createEntityManager();
		em.getTransaction().begin();
		Instituicao ins = (Instituicao) em.find(Instituicao.class, instituicao.getId());
		em.remove(ins);
		em.getTransaction().commit();
		em.close();
	}

	public static void atualizarInstituicao(Instituicao instituicao) throws Exception {
		EntityManager em = FabricaDAOJPA.getInstance().getFactory().createEntityManager();
		em.getTransaction().begin();
		em.merge(instituicao);
		em.getTransaction().commit();
		em.close();
        }
}
