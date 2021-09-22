package projetocounterstrike.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

/**
 *
 * @author ruan_
 */
@Entity
@Table(name = "tb_arma")
public class Arma extends Artefato implements Serializable{
    @Column(nullable = false, length = 100)
    private String descricao;
    
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name= "tb_arma_local", joinColumns = {@JoinColumn(name = "arma_id")}, inverseJoinColumns = {@JoinColumn(name = "municao_id")})
    private List<Municao> municao;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
    
    public Arma(){
        
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Municao> getMunicao() {
        return municao;
    }

    public void setMunicao(List<Municao> municao) {
        this.municao = municao;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
    
}
