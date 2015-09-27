package esse.chat.modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Instituicao.class)
public abstract class Instituicao_ {

	public static volatile CollectionAttribute<Instituicao, Email> emails;
	public static volatile CollectionAttribute<Instituicao, Curso> cursos;
	public static volatile CollectionAttribute<Instituicao, Fone> fones;
	public static volatile SingularAttribute<Instituicao, Endereco> endereco;
	public static volatile SingularAttribute<Instituicao, String> nome;
	public static volatile SingularAttribute<Instituicao, Long> id;
	public static volatile SingularAttribute<Instituicao, String> cnpj;

}

