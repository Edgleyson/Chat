package esse.chat.modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="TB_CURSO")
public class Curso implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "TXT_NOME")
    private String nome;
    
    @ManyToOne(fetch = FetchType.LAZY, optional=false)
    @JoinColumn(name="ID_INSTITUICAO", referencedColumnName="ID")
    private Instituicao instituicao;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="TB_CURSO_DISCIPLINA", joinColumns={
        @JoinColumn(name="ID_CURSO")},
            inverseJoinColumns={
                @JoinColumn(name="ID_DISCIPLINA")})
    private Collection<Disciplina> disciplinas;

    public Curso() {
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

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
        this.instituicao.adicionaCurso(this);
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
        if (!(object instanceof Email)) {
            return false;
        }
        final Curso other = (Curso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        } else if (this.id == null && other.id == null) {
            return this.nome.equals(other.nome);
        }
        
        return true;
    }



    @Override
    public String toString() {
        return "Curso{" + "id=" + id + ", nome=" + nome + "}";
    }
    
    
    
    
    
    
    
    
}