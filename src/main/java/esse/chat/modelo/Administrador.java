package esse.chat.modelo;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="TB_ADMINISTRADOR")
@DiscriminatorValue(value="AD")
@PrimaryKeyJoinColumn(name="ID_USUARIO", referencedColumnName="ID")
public class Administrador extends Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    public Administrador() {
        super();
    }
}
