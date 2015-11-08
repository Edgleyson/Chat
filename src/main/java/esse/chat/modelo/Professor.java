package esse.chat.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.Valid;

@Entity
@Table(name="TB_PROFESSOR")
@DiscriminatorValue(value="PF")
@PrimaryKeyJoinColumn(name="ID_USUARIO", referencedColumnName="ID")
public class Professor extends Usuario implements Serializable {
      
    private static final long serialVersionUID = 1L;
    @Valid
    @OneToMany(mappedBy = "professor", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Disciplina> disciplinas;

    public Professor() {
        super();
        this.disciplinas = new ArrayList<>();
    }
    
    public Collection<Disciplina> getDisciplinas() {
        return disciplinas;
    }
    
    public void setDisciplinas(Collection<Disciplina> disciplinas) {
        for (Disciplina disciplina : disciplinas) {
            this.adicionaDisciplina(disciplina);
        }
    }
    
    public void adicionaDisciplina(Disciplina disciplina){
        if (!this.disciplinas.contains(disciplina))
            disciplinas.add(disciplina);
        disciplina.setProfessor(this);
    }
    
    public void removeDisciplina(Disciplina disciplina){
        if (disciplinas != null) {
            disciplinas.remove(disciplina);
        }
    }  
 
}
