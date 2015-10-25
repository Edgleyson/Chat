package esse.chat.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    }
    
    public void removeDisciplina(Disciplina disciplina){
        if (disciplinas != null) {
            disciplinas.remove(disciplina);
        }
    }    
        
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Professor)) {
            return false;
        }
        Professor other = (Professor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
 
}
