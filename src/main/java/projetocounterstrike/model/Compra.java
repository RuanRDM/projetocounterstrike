package projetocounterstrike.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ruan_
 */
@Entity
@Table(name = "tb_compra")
public class Compra implements Serializable{
    @Id
    @SequenceGenerator(name = "seq_compra", sequenceName = "seq_compra_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_compra", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar data;
    
    @Column(nullable = false, precision = 2)
    private Float valorTotal;
    
    @OneToMany(mappedBy = "compra")
    private List<ItensCompra> itens;
    
    @ManyToOne
    @JoinColumn(name = "jogador_id", nullable = false)
    private Jogador jogador;
    
    public Compra(){
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data=data;
    }
    
    public void setData2(java.sql.Date data) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(data.getTime());
        this.data=cal;
    }
    

    public Float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<ItensCompra> getItens() {
        return itens;
    }

    public void setItens(List<ItensCompra> itens) {
        this.itens = itens;
    }
    
    public void setItem(ItensCompra item) {
        if (this.itens == null)
            this.itens = new ArrayList();        
        this.itens.add(item);
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

}
