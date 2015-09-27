package esse.chat.modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Professor.class)
public abstract class Professor_ extends esse.chat.modelo.Usuario_ {

	public static volatile CollectionAttribute<Professor, Disciplina> disciplinas;
	public static volatile SingularAttribute<Professor, Long> id;

}

