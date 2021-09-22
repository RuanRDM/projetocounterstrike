package projetocounterstrike.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 *
 * @author ruan_
 */
@Entity
@Table(name = "tb_artefato")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipoArtefato")
public class Artefato implements Serializable{
    @Id
    private Integer id;
    
    @Column(nullable = false, length = 200) 
    private String nome;
    
    @Column(nullable = false, precision = 2)
    private Float valor;
    
    public Artefato(){
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

}
