package esse.chat.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="TB_DISCIPLINA")
public class Disciplina implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size (max = 255)
    @Column (name = "TXT_NOME")
    private String nome;  
    @Valid
    @ManyToOne(fetch = FetchType.LAZY, optional=false)
    @JoinColumn(name="ID_CURSO", referencedColumnName="ID")
    private Curso curso;
    @Valid
    @ManyToOne(fetch = FetchType.LAZY, optional=false)
    @JoinColumn(name="ID_PROFESSOR", referencedColumnName="ID")
    private Professor professor;    
    @Valid
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="TB_DISCIPLINA_ALUNO", joinColumns={
        @JoinColumn(name="ID_DISCIPLINA")},
            inverseJoinColumns={
                @JoinColumn(name="ID_ALUNO")})    
    private Collection<Aluno> alunos; 
    @Valid
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "ID_SALA_CHAT", referencedColumnName = "ID")
    private SalaDeChat chat;
    
    @Valid
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="TB_DISCIPLINA_MONITOR", joinColumns={
        @JoinColumn(name="ID_DISCIPLINA")},
            inverseJoinColumns={
                @JoinColumn(name="ID_ALUNO")})
    private Collection<Aluno> monitores;

    public Disciplina() {
        this.alunos = new ArrayList<>();
        this.monitores = new ArrayList<>();
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public SalaDeChat getChat(){
        return chat;
    }
    public void setChat(SalaDeChat chat){
        this.chat = chat;
        this.chat.setDisciplina(this);
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
    
    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    
    public Collection<Aluno> getAlunos() {
        return alunos;
    }
    
    public void setAlunos(Collection<Aluno> alunos) {
        for (Aluno aluno : alunos) {
            this.adicionaAluno(aluno);
        }
    }
    
    public void adicionaAluno(Aluno aluno){
        if (!this.alunos.contains(aluno))
            if(aluno.adicionaDisciplina(this)){
                alunos.add(aluno);
            }
    }
    
    public void removeAluno(Aluno aluno){
        if (alunos != null) {
            if(aluno.removeDisciplina(this)){
                alunos.remove(aluno);
            }
        }
    } 
    
    public Collection<Aluno> getMonitores() {
        return monitores;
    }
    
    public void setMonitores(Collection<Aluno> monitores) {
        for (Aluno aluno : monitores) {
            this.adicionaMonitor(aluno);
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
    public boolean equals(Object object) {
        if (!(object instanceof Disciplina)) {
            return false;
        }
        Disciplina other = (Disciplina) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        } else if (this.id == null && other.id == null) {
            return this.nome.equals(other.nome);
        }
        
        return true;
    }

    
    
    @Override
    public String toString() {
        return "Disciplina{" + "id=" + id + ", nome=" + nome +  '}';
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}