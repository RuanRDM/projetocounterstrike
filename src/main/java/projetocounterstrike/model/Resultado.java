package projetocounterstrike.model;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ruan_
 */
@Entity
@Table(name = "tb_resultado")
public class Resultado implements Serializable{
    @EmbeddedId
    private ResultadoID id;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar data;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
    
    public Resultado(){
        
    } 

    public ResultadoID getId() {
        return id;
    }

    public void setId(ResultadoID id) {
        this.id = id;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
