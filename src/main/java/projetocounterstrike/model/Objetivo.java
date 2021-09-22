package projetocounterstrike.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 *
 * @author ruan_
 */
@Entity
@Table(name = "tb_objetivo")
public class Objetivo implements Serializable {
    @Id
    @SequenceGenerator(name = "seq_objetivo", sequenceName = "seq_objetivo_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_objetivo", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(nullable = false, length = 100)
    private String descricao;
    
    @Column(nullable = false)
    private Integer pontos;
    
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name= "tb_objetivo_local", joinColumns = {@JoinColumn(name = "objetivo_id")}, inverseJoinColumns = {@JoinColumn(name = "local_id")})
    private List<Local> local;
    
    public Objetivo(){
        
    }
        
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getPontos() {
        return pontos;
    }

    public void setPontos(Integer pontos) {
        this.pontos = pontos;
    }

    public List<Local> getLocal() {
        return local;
    }

    public void setLocal(List<Local> local) {
        this.local = local;
    }
}
