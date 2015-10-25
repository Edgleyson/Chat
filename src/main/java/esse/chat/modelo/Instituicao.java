package esse.chat.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;

@Entity
@Table(name="TB_INSTITUICAO")
public class Instituicao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank                                                   
    @Size (max = 255)
    @Column(name="TXT_NOME")
    private String nome;
    @NotNull
    @Size (max = 10)
    @Column(name="TXT_SIGLA")
    private String sigla;
    @NotNull
    @CNPJ
    @Column(name="TXT_CNPJ")
    private String cnpj;
    @Valid
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "ID_ENDERECO", referencedColumnName = "ID")
    private Endereco endereco;
    @Valid
    @OneToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name="ID_INSTITUICAO", referencedColumnName="ID")
    private Collection<Fone> fones = new ArrayList<>();
    @Valid
    @OneToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name="ID_INSTITUICAO", referencedColumnName="ID")
    private Collection<Email> emails = new ArrayList<>();
    @Valid
    @OneToMany(mappedBy="instituicao", fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
    private Collection<Curso> cursos = new ArrayList<>();

    public Instituicao() {
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
    
    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Collection<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(Collection<Curso> cursos) {
        for (Curso curso : cursos) {
            this.adicionaCurso(curso);
        }
    }
    
    public void adicionaCurso(Curso curso){
        if (!this.cursos.contains(curso))
            cursos.add(curso);            
        curso.setInstituicao(this);
    }
    
    public void removeCurso(Curso curso){
        if (cursos != null) {
            cursos.remove(curso);
        }
    } 
    
    public Collection<Email> getEmails() {
        return emails;
    }

    public void setEmails(Collection<Email> emails) {
        for (Email email : emails) {
            this.adicionaEmail(email);
        }
    }
    
    public void adicionaEmail(Email email){
        if (!this.emails.contains(email)) {
            emails.add(email);
        }
    }
    
    public void removeEmail(Email email){
        if (emails != null) {
            emails.remove(email);
        }
    } 
    
    public Collection<Fone> getFones() {
        return fones;
    }
    
    public void setFones(Collection<Fone> fones) {
        for (Fone fone : fones) {
            this.adicionaFone(fone);
        }
    }
    
    public void adicionaFone(Fone fone){
        if (!this.fones.contains(fone))
            fones.add(fone);
    }
    
    public void removeFone(Fone fone){
        if (fones != null) {
            fones.remove(fone);
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
        if (!(object instanceof Instituicao)) {
            return false;
        }
        Instituicao other = (Instituicao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }else if (this.id == null && other.id == null) {
            return this.cnpj.equals(other.cnpj);
        }
        
        return true;
    }

    @Override
    public String toString() {
        return "Instituicao{" + "id=" + id + ", nome=" + nome + ", sigla=" + sigla + ", cnpj=" + cnpj + '}';
    }

        
}
