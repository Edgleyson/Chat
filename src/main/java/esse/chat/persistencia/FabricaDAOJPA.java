package esse.chat.persistencia;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class FabricaDAOJPA {
	private static final String PERSISTENCE_UNIT_NAME = "ChatPU";
	private EntityManagerFactory factory;
	private static FabricaDAOJPA instance = null;
	
	private FabricaDAOJPA(){
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}
	
	public EntityManagerFactory getFactory(){
		return factory;
	}
	
	public static FabricaDAOJPA getInstance(){
		if(instance == null){
			instance = new FabricaDAOJPA();
		}
		return instance;
	}
	private void destroyFactory(){
		factory.close();		
	}
	
	public static void destroyInstance(){
		if(instance != null){
			instance.destroyFactory();
			instance = null;
		}
	}
}

