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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.JoinTable;

/**
 *
 * @author ruan_
 */
@Entity
@Table(name = "tb_mapa")
public class Mapa implements Serializable {
    @Id
    @SequenceGenerator(name = "seq_mapa", sequenceName = "seq_mapa_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_mapa", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(nullable = false, length = 10)
    private String nome; 
    
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name= "tb_mapa_local", joinColumns = {@JoinColumn(name = "mapa_id")}, inverseJoinColumns = {@JoinColumn(name = "local_id")})
    private List<Local> locais;
    
    public Mapa(){
        
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

    public List<Local> getLocais() {
        return locais;
    }

    public void setLocais(List<Local> locais) {
        this.locais = locais;
    }
}
