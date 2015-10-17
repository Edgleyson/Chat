package esse.chat.modelo;

import esse.chat.controle.ValidaEstado;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;


@Entity
@Table(name="TB_ENDERECO")
public class Endereco implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(max = 255)
    @Column(name="TXT_LOGRADOURO")
    private String logradouro;
    @NotNull
    @Min(1)
    @Max(99999)
    @Column(name="TXT_NUMERO")
    private String numero;
    @Size (max = 150) 
    @Column(name="TXT_COMPLEMENTO")
    private String complemento;
    @NotBlank
    @Size (max = 150)
    @Column(name="TXT_BAIRRO")
    private String bairro;
    @NotNull
    @Pattern(regexp = "[0-9]{5}-[0-9]{3}", message = "Cep Obrigatorio")
    @Column(name="TXT_CEP")
    private String cep;
    @NotBlank
    @Size(max = 150)
    @Column(name="TXT_CIDADE")
    private String cidade;
    @NotBlank
    @ValidaEstado
    @Size(min = 2, max = 2)
    @Column(name="TXT_ESTADO")
    private String estado;

    public Endereco() {
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Endereco)) {
            return false;
        }
        Endereco other = (Endereco) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Endereço: "+  logradouro+", "+ numero+", "+ complemento+", "+bairro+", "+ cidade+"/"+ estado+ '}';
    }
    
    public Endereco(String l, String n, String comp, String b,String cep, String c, String e, String p){
        this.logradouro = l;
        this.numero = n;
        this.complemento = comp;
        this.bairro = b;
        this.cep = l;
        this.cidade = c;
        this.estado = e;
    }
}
