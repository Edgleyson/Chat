package esse.chat.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="TB_ALUNO")
@DiscriminatorValue(value="AL")
@PrimaryKeyJoinColumn(name="ID_USUARIO", referencedColumnName="ID")
@NamedQueries(
        @NamedQuery(
                name = "Aluno.PorMatricula",
                query = "SELECT a FROM Aluno a WHERE a.matricula LIKE :matricula ORDER BY a.primeiroNome"
        )
)
public class Aluno extends Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
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
    
    public void setDisciplinas(Collection<Disciplina> disciplinas){
        for (Disciplina disciplina : disciplinas) {
            this.adicionaDisciplina(disciplina);
        }
    }
       
    public void adicionaDisciplina(Disciplina disciplina){
        if (!this.disciplinas.contains(disciplina)){
            disciplinas.add(disciplina);
        } 
    }
    
    public void removeDisciplina(Disciplina disciplina){
        if (disciplinas != null) {
            disciplinas.remove(disciplina);
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", matricula: " + matricula;
    }
    
}
