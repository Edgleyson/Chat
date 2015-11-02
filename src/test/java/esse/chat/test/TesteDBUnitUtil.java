package esse.chat.test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class TesteDBUnitUtil {
    
    private static EntityManagerFactory emf;

    public static void main(String[] args) {
        emf = Persistence.createEntityManagerFactory("ChatPU");
        DbUnitUtil.inserirDados();
        emf.close();
    }
}
