package esse.chat.modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name="TB_DISCIPLINA")
public class Disciplina implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "TXT_NOME")
    private String nome;    
    
    @ManyToOne(fetch = FetchType.LAZY, optional=false)
    @JoinColumn(name="ID_DISCIPLINA", referencedColumnName="ID")
    private Professor professor;    
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="TB_DISCIPLINA_ALUNO", joinColumns={
        @JoinColumn(name="ID_DISCIPLINA")},
            inverseJoinColumns={
                @JoinColumn(name="ID_ALUNO")})
    private Collection<Aluno> alunos;    
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="TB_DISCIPLINA_MONITOR", joinColumns={
        @JoinColumn(name="ID_DISCIPLINA")},
            inverseJoinColumns={
                @JoinColumn(name="ID_ALUNO")})
    private Collection<Aluno> monitores;

    public Disciplina() {
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Professor getProfessor() {
        return professor;
    }


    public void setProfessor(Professor professor) {
        this.professor = professor;
        this.professor.adicionaDisciplina(this);
    }
    
    public void adicionaAluno(Aluno aluno){
        if (!this.alunos.contains(aluno))
            alunos.add(aluno);
    }
    
    public void removeAluno(Aluno aluno){
        if (alunos != null) {
            alunos.remove(aluno);
        }
    } 
    
    public void adicionaMonitor(Aluno aluno){
        if (!this.alunos.contains(aluno))
            alunos.add(aluno);
    }
    
    public void removeMonitor(Aluno aluno){
        if (monitores != null) {
            monitores.remove(aluno);
        }
    } 

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

 

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Disciplina other = (Disciplina) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.professor, other.professor)) {
            return false;
        }
        if (!Objects.equals(this.alunos, other.alunos)) {
            return false;
        }
        return true;
    }

    
    
    @Override
    public String toString() {
        return "Disciplina{" + "id=" + id + ", nome=" + nome +  '}';
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}