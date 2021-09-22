package projetocounterstrike.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author ruan_
 */
@Entity
@Table(name = "tb_itensCompra")
public class ItensCompra implements Serializable{
    @Id
    @SequenceGenerator(name = "seq_itensCompra", sequenceName = "seq_itensCompra_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_itensCompra", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(nullable = false)
    private Integer quantidade;
    
    @Column(nullable = false, precision = 2)
    private Float valor;
    
    @ManyToOne
    @JoinColumn(name = "compra_id", nullable = false)
    private Compra compra;
    
    @ManyToOne
    @JoinColumn(name = "artefato_id", nullable = false)
    private Artefato artefato;
    
    public ItensCompra(){
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor * quantidade;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Artefato getArtefato() {
        return artefato;
    }

    public void setArtefato(Artefato artefato) {
        this.artefato = artefato;
    }
}
