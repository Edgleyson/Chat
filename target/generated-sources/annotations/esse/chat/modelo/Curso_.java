package esse.chat.modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Curso.class)
public abstract class Curso_ {

	public static volatile CollectionAttribute<Curso, Disciplina> disciplinas;
	public static volatile SingularAttribute<Curso, Instituicao> instituicao;
	public static volatile SingularAttribute<Curso, String> nome;
	public static volatile SingularAttribute<Curso, Long> id;

}

