package esse.chat.modelo;

import esse.chat.controle.ValidaApelido;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
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
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

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
    @NotBlank
    @Size(max = 30)
    @Pattern(regexp = "\\p{Upper}{1}\\p{Lower}+", message = "{esse.chat.Usuario.primeiroNome}")
    @Column(name="TXT_NOME")
    private String primeiroNome;
    @NotBlank
    @Size(max = 30)
    @Pattern(regexp = "\\p{Upper}{1}\\p{Lower}+", message = "{esse.chat.Usuario.ultimoNome}")
    @Column(name="TXT_SOBRENOME")
    private String ultimoNome;
    @NotBlank
    @ValidaApelido
    @Size (min=3, max = 10)
    @Column(name="TXT_APELIDO")
    private String apelido;
    @NotBlank 
    @Size (max = 1)
    @Pattern(regexp = "(M|F)", message = "{esse.chat.Usuario.sexo}")
    @Column(name="TXT_SEXO")
    private String sexo;
    @NotBlank 
    @Size (min = 4, max = 10)
    @Column(name="TXT_SENHA")
    private String senha;
    @NotNull
    @Past
    @Column(name="DT_NASCIMENTO")
    @Temporal(TemporalType.DATE)
    private Date nascimento;
    @NotNull
    @CPF
    @Column(name="TXT_CPF")
    private String cpf;    
    @Transient
    private int idade;
    @Valid
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID")
    private Endereco endereco;
    @Valid
    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name="ID_USUARIO", referencedColumnName="ID")
    private Collection<Email> emails;
    @Valid
    @OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name="ID_USUARIO", referencedColumnName="ID")
    private Collection<Fone> fones;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String nome) {

        this.primeiroNome = nome;
    }
    
    public String getUltimoNome() {
        return ultimoNome;
    }

    public void setUltimoNome(String nome) {
        this.ultimoNome = nome;
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
        Calendar dataNascimento = Calendar.getInstance();
        dataNascimento.setTime(nascimento);
        Calendar hoje = Calendar.getInstance();

        this.idade = hoje.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR);


        if (hoje.get(Calendar.MONTH) < dataNascimento.get(Calendar.MONTH)) {
            idade--;
        } else {
            if (hoje.get(Calendar.MONTH) == dataNascimento.get(Calendar.MONTH) && hoje.get(Calendar.DAY_OF_MONTH) < dataNascimento.get(Calendar.DAY_OF_MONTH)) {
                idade--;
            }
        }
        return idade;
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
        return "Usuario: " + "id=" + id + ", nome=" + primeiroNome + " " + ultimoNome + ", apelido=" + apelido +
                ", sexo=" + sexo + ", senha=" + senha + ", nascimento=" + nascimento + 
                ", cpf=" + cpf + endereco.toString() + ", fone=" + sb.toString() +
                ", email=" + sb2.toString();
}
    
}
