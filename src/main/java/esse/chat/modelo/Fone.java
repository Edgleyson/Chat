package esse.chat.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="TB_FONE")
public class Fone implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="TXT_FONE")
    private String fone;
    
//    @ManyToOne(cascade=CascadeType.ALL, optional=false)
//    private Instituicao instituicao;
//
//    public Instituicao getInstituicao() {
//        return instituicao;
//    }
//
//    public void setInstituicao(Instituicao instituicao) {
//        this.instituicao = instituicao;
//    }

    public Fone() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }
    
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Fone)) {
            return false;
        }
        Fone other = (Fone) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        } else if (this.id == null && other.id == null) {
            return this.fone.equals(other.fone);
        }
        
        return true;
    }

    @Override
    public String toString() {
        return "FoneUsuario{" + "id=" + id + ", fone=" + fone + '}';
    }
    
}