package esse.chat.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="TB_ALUNO")
@DiscriminatorValue(value="AL")
@PrimaryKeyJoinColumn(name="ID_USUARIO", referencedColumnName="ID")
public class Aluno extends Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    @NotBlank
    @Size(max = 20)
    @Column(name="TXT_MATRICULA")
    private String matricula;
    @Valid
    @ManyToMany(mappedBy = "alunos")
    private Collection<Disciplina> disciplinas;
    

    public Aluno() {
        super();  
        this.disciplinas = new ArrayList<>();
    }
    
    public String getMatricula() {
        return matricula;
    }
    
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    } 
    
    public Collection<Disciplina> getDisciplinas() {
        return disciplinas;
    }
       
    public boolean adicionaDisciplina(Disciplina disciplina){
        if (!this.disciplinas.contains(disciplina)){
            return disciplinas.add(disciplina);
        }            
        return false;
    }
    
    public boolean removeDisciplina(Disciplina disciplina){
        if (disciplinas != null) {
            return disciplinas.remove(disciplina);
        }
        return false;
    }

//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (id != null ? id.hashCode() : 0);
//        return hash;
//    }

//    @Override
//    public boolean equals(Object object) {
//        if (!(object instanceof Aluno)) {
//            return false;
//        }
//        Aluno other = (Aluno) object;
//        if ((id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }else if (this.id == null && other.id == null) {
//            return this.matricula.equals(other.matricula);
//        }
//        
//        return true;
//    }

    @Override
    public String toString() {
        return super.toString() + ", matricula: " + matricula;
    }
    
}
