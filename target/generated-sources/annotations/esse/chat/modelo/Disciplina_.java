package esse.chat.modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Disciplina.class)
public abstract class Disciplina_ {

	public static volatile SingularAttribute<Disciplina, Professor> professor;
	public static volatile CollectionAttribute<Disciplina, Aluno> alunos;
	public static volatile SingularAttribute<Disciplina, String> nome;
	public static volatile SingularAttribute<Disciplina, Long> id;
	public static volatile CollectionAttribute<Disciplina, Aluno> monitores;

}

