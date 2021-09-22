package projetocounterstrike.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 *
 * @author ruan_
 */
@Entity
@Table(name = "tb_municao")
public class Municao extends Artefato implements Serializable{
    @Column(nullable = false, length = 100)
    private String descricao;
    
    @Column(nullable = false)
    private Integer qtdPente;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Calibre calibre;
    
    public Municao(){
        
    }    

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQtdPente() {
        return qtdPente;
    }

    public void setQtdPente(Integer qtdPente) {
        this.qtdPente = qtdPente;
    }

    public Calibre getCalibre() {
        return calibre;
    }

    public void setCalibre(Calibre calibre) {
        this.calibre = calibre;
    }
    
}
