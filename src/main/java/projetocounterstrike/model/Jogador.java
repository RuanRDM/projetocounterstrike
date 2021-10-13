package projetocounterstrike.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.JoinTable;

/**
 *
 * @author ruan_
 */
@Entity
@Table(name = "tb_jogador")
public class Jogador implements Serializable {
    @Id
    @SequenceGenerator(name = "seq_jogador", sequenceName = "seq_jogador_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_jogador", strategy = GenerationType.SEQUENCE)
    private String nickname;
    
    @Column(nullable = false, length = 10)
    private String senha;
    
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar data_cadastro;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar data_ultimo_login;
    
    @Column(nullable = false)
    private Integer pontos;
    
    @OneToMany(mappedBy = "jogador")
    private List<Compra> compra;
    
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name= "tb_jogador_artefato", joinColumns = {@JoinColumn(name = "jogador_id")}, inverseJoinColumns = {@JoinColumn(name = "artefato_id")})
    private List<Artefato> artefatos;
    
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name= "tb_jogador_patente", joinColumns = {@JoinColumn(name = "jogador_id")}, inverseJoinColumns = {@JoinColumn(name = "patente_id")})
    private List<Patente> patentes;
    
    public Jogador(){
        
    }
    
    public Jogador(String nickname){
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Calendar getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(Calendar data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    public Calendar getData_ultimo_login() {
        return data_ultimo_login;
    }

    public void setData_ultimo_login(Calendar data_ultimo_login) {
        this.data_ultimo_login = data_ultimo_login;
    }

    public Integer getPontos() {
        return pontos;
    }

    public void setPontos(Integer pontos) {
        this.pontos = pontos;
    }

    public List<Compra> getCompra() {
        return compra;
    }

    public void setCompra(List<Compra> compra) {
        this.compra = compra;
    }

    public List<Artefato> getArtefatos() {
        return artefatos;
    }

    public void setArtefatos(List<Artefato> artefatos) {
        this.artefatos = artefatos;
    }

    public List<Patente> getPatentes() {
        return patentes;
    }

    public void setPatentes(List<Patente> patentes) {
        this.patentes = patentes;
    }
    
    @Override
    public String toString(){
        return nickname;
    }
    
   @Override
    public boolean equals(Object o){

        if(o == null){
            return false;

        }else if(!(o instanceof Jogador)){
            return false;

        }else{
            Jogador j = (Jogador) o;
            if (j.getNickname() == this.getNickname()){
                return true;
            }else{
                return false;
            }
        }
    }
    
}
