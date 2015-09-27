
package esse.chat.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Edgleyson
 */
@Entity
@Table(name="TB_EMAIL")
public class Email implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="TXT_EMAIL")
    private String email;
    
//    @ManyToOne(fetch=FetchType.LAZY, optional=false)
//    private Instituicao instituicao;
//
//    public Instituicao getInstituicao() {
//        return instituicao;
//    }
//
//    public void setInstituicao(Instituicao instituicao) {
//        this.instituicao = instituicao;
//    }

    public Email() {
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Email)) {
            return false;
        }
        Email other = (Email) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        } else if (this.id == null && other.id == null) {
            return this.email.equals(other.email);
        }
        
        return true;
    }

    @Override
    public String toString() {
        return "EmailUsuario{" + "id=" + id + ", email=" + email + '}';
    }
    
}