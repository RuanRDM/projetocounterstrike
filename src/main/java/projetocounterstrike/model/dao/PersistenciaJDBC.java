package projetocounterstrike.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import projetocounterstrike.model.Artefato;
import projetocounterstrike.model.Compra;
import projetocounterstrike.model.ItensCompra;
import projetocounterstrike.model.Jogador;

/**
 *
 * @author ruan_
 */
public class PersistenciaJDBC implements InterfacePersistencia{
    private final String DRIVER = "org.postgresql.Driver";
    private final String USER = "postgres";
    private final String SENHA = "3011";
    private final String URL = "jdbc:postgresql://localhost:5432/db_projetocounterstrike";
    
    public Connection con = null; 
    
    public PersistenciaJDBC() throws Exception{
        Class.forName(DRIVER); //carregamento do driver postgresql
        System.out.println("Tentando estabelecer a conexao JDBC com: "+URL+" ...");
        this.con = (Connection) DriverManager.getConnection(URL,USER,SENHA);
        
    }

    @Override
    public Boolean conexaoAberta() {
        try {
            if(con != null)
                return !con.isClosed(); //verifica se a conexao esta aberta
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public void fecharConexao() {
        try {
            this.con.close();
            System.out.println("Fechou conexao JDBC");
        } catch (SQLException ex) {
            ex.printStackTrace(); //gera pilha de erro
        }
    }

    @Override
    public Object find(Class c, Object id) throws Exception {
        if(c == Compra.class){
            // recuperar/selecionar uma determinada compra
            //em seguida recuperar os itens dessa compra.
            Compra compra = null;
            
            // executar um select em tb_compra
            PreparedStatement ps = 
            this.con.prepareStatement("select c.id, c.data, c.valortotal, c.jogador_id " + " from tb_compra c " + " where c.id = ? ");
            ps.setInt(1, new Integer(id.toString()));
            ResultSet rs = ps.executeQuery();//executa a query
            
            if(rs.next()){
                
                compra = new Compra();//inicialização do objeto que será retornado.
                compra.setId(rs.getInt("id"));
                compra.setData2(rs.getDate("data"));
                compra.setValorTotal(rs.getFloat("valortotal"));
                Jogador j = new Jogador();
                j.setNickname(rs.getString("jogador_id"));
                compra.setJogador(j);
                
                ps.close();//fecha o cursor
                
                // executar um select envolvendo tb_itenscompra.
                ps = this.con.prepareStatement("select ic.id, ic.quantidade, ic.valor, ic.artefato_id, ic.compra_id " + " from tb_itenscompra ic " + " where ic.compra_id = ? ");  
                ps.setInt(1, compra.getId());
                
                rs = ps.executeQuery();
                while(rs.next()){
                    
                    ItensCompra ic = new ItensCompra();
                    ic.setId(rs.getInt("id"));
                    ic.setQuantidade(rs.getInt("quantidade"));
                    ic.setValor(rs.getFloat("valor"));
                    
                    Artefato a = new Artefato();
                    a.setId(rs.getInt("artefato_id"));
                    ic.setArtefato(a);
                    
                    Compra co = new Compra();
                    co.setId(rs.getInt("compra_id"));
                    ic.setCompra(co);
                    
                    compra.setItem(ic);//adiciona na compra o item                    
                }
                rs.close();//fecha o cursor
                ps.close();//fecha
            }
                    
            return compra;
        }else if (c == ItensCompra.class){
            PreparedStatement ps = 
            this.con.prepareStatement("select i.id, i.quantidade, i.valor, i.artefato_id, i.compra_id " + "from tb_itenscompra i " + "where id = ? ");
                        
            ps.setInt(1, Integer.parseInt(id.toString()));
            
            ResultSet rs = ps.executeQuery();//o ponteiro do ResultSet inicialmente está na linha -1
            
            if(rs.next()){//se a matriz (ResultSet) tem uma linha
                
                ItensCompra i = new ItensCompra();
                i.setId(rs.getInt("id"));
                i.setQuantidade(rs.getInt("quantidade"));
                i.setValor(rs.getFloat("valor"));
                
                Artefato at = new Artefato();
                at.setId(rs.getInt("artefato_id"));
                i.setArtefato(at);
                    
                Compra cp = new Compra();
                cp.setId(rs.getInt("compra_id"));
                i.setCompra(cp);
                return i;
            }            
        }
        return null;
    }

    @Override
    public void persist(Object o) throws Exception {
        if(o instanceof Compra){
            Compra compra = (Compra) o;
            
            //descobrir se é insert ou update
            if(compra.getId() == null){
                
                //prepara a instrução.
                PreparedStatement ps = this.con.prepareStatement("insert into tb_compra (id, data, valortotal, jogador_id) values (nextval('seq_compra_id'), ?, ?, ?) returning id; "); 
                ps.setDate(1, new java.sql.Date(compra.getData().getTimeInMillis()));
                ps.setFloat(2, compra.getValorTotal());
                ps.setString(3, compra.getJogador().getNickname());
                
                //insert em tb_compra
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    
                    compra.setId(rs.getInt("id"));//seta o id gerado pela sequence.
                    
                    if(compra.getItens()!= null && compra.getItens().size() > 0){
                        
                        for(ItensCompra i : compra.getItens()){
                            
                            ps = this.con.prepareStatement("insert into tb_itenscompra (id, quantidade, valor, artefato_id, compra_id) values (?, ?, ?, ?, ?)");
                            ps.setInt(1, i.getId());
                            ps.setInt(2, i.getQuantidade());
                            ps.setFloat(3, i.getValor());
                            ps.setInt(4, i.getArtefato().getId());
                            ps.setInt(5, compra.getId());
                            
                            //insert em tb_itenscompra
                            ps.execute();
                        }
                    }                                        
                }
                rs.close();//fecha o cursor
                ps.close();
                
            }else{
                
                //estratégia para alteração: remove tudo e depois insere novamente.
                
                PreparedStatement ps = this.con.prepareStatement("update tb_compra set valortotal = ?, data = ?, jogador_id=? where id = ?"); 
                ps.setFloat(1, compra.getValorTotal());
                ps.setDate(2, new java.sql.Date(compra.getData().getTimeInMillis()));
                ps.setString(3, compra.getJogador().getNickname());
                ps.setInt(4, compra.getId());
                ps.execute();
                
                ps = this.con.prepareStatement("delete from tb_itenscompra where compra_id = ? ");
                ps.setInt(1, compra.getId());
                ps.execute();
                
                if(compra.getItens()!= null && compra.getItens().size() > 0){

                    for(ItensCompra i : compra.getItens()){

                        ps = this.con.prepareStatement("insert into tb_itenscompra (id, compra_id) values (?, ?, ?, ?, ?) ");
                        ps.setInt(1, i.getId());
                        ps.setInt(2, compra.getId());

                        //insert em tb_itenscompra
                        ps.execute();
                    }
                    
                    ps.close();
                }                                                
            }            
            
            
        }else if( o instanceof ItensCompra){
            ItensCompra itens = (ItensCompra) o;
            if(itens.getId()==null){
                PreparedStatement ps = this.con.prepareStatement("insert into tb_itenscompra (id, quantidade, valor, artefato_id, compra_id) values (nextval('seq_itenscompra_id'), ?, ?, ?, ?); ");
                ps.setInt(1, itens.getQuantidade());
                ps.setFloat(2, itens.getValor());
                ps.setInt(3, itens.getArtefato().getId());
                ps.setInt(4, itens.getCompra().getId());
                
                ps.execute();
                
                ps.close();
            }

        }
    }

    @Override
    public void remover(Object o) throws Exception {
            if(o instanceof Compra){
            //delete em tb_itenscompra
            Compra compra = (Compra) o;
            PreparedStatement ps = this.con.prepareStatement("delete from tb_itenscompra where compra_id = ? ");
            ps.setInt(1, compra.getId());
            ps.execute();

            //delete em tb_compra.
            ps = this.con.prepareStatement("delete from tb_compra where id = ? ");
            ps.setInt(1, compra.getId());
            ps.execute();
                            
            ps.close();
            
        }
    }

    @Override
    public Jogador doLogin(String nickname, String senha) throws Exception {
        Jogador usuario = null;
        
         PreparedStatement ps = 
            this.con.prepareStatement("select u.nickname, u.senha from tb_jogador u where u.nickname = ? and u.senha = ? ");
                        
            ps.setString(1, nickname);
            ps.setString(2, senha);
            
            ResultSet rs = ps.executeQuery();//o ponteiro do REsultSet inicialmente está na linha -1
            
            if(rs.next()){//se a matriz (ResultSet) tem uma linha
                usuario = new Jogador();
                usuario.setNickname(rs.getString("nickname"));
            }
            ps.close();
            return usuario;
    }

    @Override
    public List<Jogador> getJogador() throws Exception {
        List<Jogador> lista = null;
        
        // executar um select em tb_compra
        PreparedStatement ps = this.con.prepareStatement("select c.nickname" + " from tb_jogador c " + " order by c.nickname asc ");        
        ResultSet rs = ps.executeQuery();//executa a query
        
        lista = new ArrayList();
        
        while(rs.next()){

            Jogador jg = new Jogador();//inicialização do objeto que será retornado.
            jg.setNickname(rs.getString("nickname"));    

            lista.add(jg);
        }
        
        rs.close();
        ps.close();//fecha o cursor
        
        return lista;
    }
    
    @Override
    public List<Compra> getCompras() throws Exception {
        List<Compra> lista = null;
        
        // executar um select em tb_compra
        PreparedStatement ps = this.con.prepareStatement("select c.id, c.data, c.valortotal, c.jogador_id " + " from tb_compra c " + " order by c.id asc ");        
        ResultSet rs = ps.executeQuery();//executa a query
        
        lista = new ArrayList();
        
        while(rs.next()){

            Compra compra = new Compra();//inicialização do objeto que será retornado.
            compra.setId(rs.getInt("id"));
            compra.setData2(rs.getDate("data"));
            compra.setValorTotal(rs.getFloat("valortotal"));
            Jogador j = new Jogador();
            j.setNickname(rs.getString("jogador_id"));
            compra.setJogador(j);         

            // executar um select envolvendo tb_itenscompra.
            PreparedStatement ps2 = this.con.prepareStatement("select ic.quantidade " + " from tb_itenscompra ic, tb_compra c " + " where ic.compra_id=c.id and c.id = ? ");  
            ps2.setInt(1, compra.getId());

            ResultSet rs2 = ps2.executeQuery();
            while(rs2.next()){

                ItensCompra ic = new ItensCompra();
                //ic.setId(rs2.getInt("id"));
                ic.setQuantidade(rs2.getInt("quantidade"));
                //ic.setValor(rs2.getFloat("valor"));
                    
                //Artefato a = new Artefato();
                //a.setId(rs2.getInt("artefato_id"));
                //ic.setArtefato(a);
                    
                //Compra co = new Compra();
                //co.setId(rs2.getInt("compra_id"));
                //ic.setCompra(co);
                    
                compra.setItem(ic);//adiciona na compra o item 
            }
            rs2.close();//fecha o cursor
            ps2.close();//fecha
            
            lista.add(compra);
        }
        
        rs.close();
        ps.close();//fecha o cursor
        
        return lista;
    }
}
