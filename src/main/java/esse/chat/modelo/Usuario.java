package esse.chat.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="TB_USUARIO")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DISC_USUARIO",
        discriminatorType = DiscriminatorType.STRING, length = 2)
@Access(AccessType.FIELD)
public abstract class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="TXT_NOME")
    private String nome;
    @Column(name="TXT_APELIDO")
    private String apelido;
    @Column(name="TXT_SEXO")
    private String sexo;
    @Column(name="TXT_SENHA")
    private String senha;
    @Column(name="DT_NASCIMENTO")
    @Temporal(TemporalType.DATE)
    private Date nascimento;
    @Column(name="TXT_CPF")
    private String cpf;    
    @Transient
    private int idade;
    
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID")
    private Endereco endereco;
    
    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name="ID_USUARIO", referencedColumnName="ID")
    private Collection<Email> emails;
    
    @OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name="ID_USUARIO", referencedColumnName="ID")
    private Collection<Fone> fones;

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

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getIdade(Date nascimento) {
        //escrever metodo para calcular
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
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
        if (!this.emails.contains(email))
            emails.add(email);
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();  
        for (Object obj : fones) {  
           sb.append(obj.toString()).append(" ");  
        }
        StringBuilder sb2 = new StringBuilder();
        for (Object obj : emails) {  
           sb2.append(obj.toString()).append(" ");  
        } 
        return "Usuario: " + "id=" + id + ", nome=" + nome + ", apelido=" + apelido +
                ", sexo=" + sexo + ", senha=" + senha + ", nascimento=" + nascimento + 
                ", cpf=" + cpf + endereco.toString() + ", fone=" + sb.toString() +
                ", email=" + sb2.toString();
    }
    
}
