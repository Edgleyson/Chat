package esse.chat.modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Usuario.class)
public abstract class Usuario_ {

	public static volatile ListAttribute<Usuario, EmailUsuario> emails;
	public static volatile SingularAttribute<Usuario, String> senha;
	public static volatile SingularAttribute<Usuario, Date> nascimento;
	public static volatile ListAttribute<Usuario, FoneUsuario> fones;
	public static volatile SingularAttribute<Usuario, String> apelido;
	public static volatile SingularAttribute<Usuario, EnderecoUsuario> endereco;
	public static volatile SingularAttribute<Usuario, String> cpf;
	public static volatile SingularAttribute<Usuario, String> nome;
	public static volatile SingularAttribute<Usuario, Long> id;
	public static volatile SingularAttribute<Usuario, String> sexo;

}

