package projetocounterstrike.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import org.junit.Test;
import projetocounterstrike.model.Artefato;
import projetocounterstrike.model.Compra;
import projetocounterstrike.model.ItensCompra;
import projetocounterstrike.model.Jogador;
import projetocounterstrike.model.dao.PersistenciaJDBC;

/**
 *
 * @author ruan_
 */
public class TestePersistenciaJDBC {
    
    
    //@Test
    public void testarConexaoJDBC() throws Exception{
        PersistenciaJDBC conexaoJDBC = new PersistenciaJDBC();
        
        if(conexaoJDBC.conexaoAberta()){
            System.out.println("Conexao com DB aberta utilizando JDBC");
            conexaoJDBC.fecharConexao();
         }else{
             System.out.println("Não foi possivel abrir a conexao com DB aberta utilizando JDBC");
         }
    }
    
    
    /* 
    Antes da execução do teste deve executar os seguintes comandos sql abaixo pois a tabela tb_compra tem como requisito a existencia do jogador que ela irá referenciar
    e a tb_itenscompra tem como requisito a existencia do artefato que o item irá referenciar.
    COMANDOS:
    INSERT INTO tb_jogador VALUES('Meed','01/01/2021','09/09/2021',100,1234);
    INSERT INTO tb_artefato VALUES('arma','01','ak47',1000);
    */
    //@Test
    public void testarPersistenciaCompra() throws Exception{
        PersistenciaJDBC conexaoJDBC = new PersistenciaJDBC();
        
        if(conexaoJDBC.conexaoAberta()){
            System.out.println("Conexao com DB aberta utilizando JDBC");

            List<Compra> listc = conexaoJDBC.getCompras();
                
            if(listc!=null && !listc.isEmpty()){
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                for(Compra c : listc){
                    System.out.println("|-----------------------------------------------------------------------------------------------------------------------------------");
                    System.out.println("|>>> Encontrou a compra id: "+c.getId()+" - Data: "+df.format(c.getData().getTime())+" - Valor Total: "+c.getValorTotal()+" - Jogador id: "+c.getJogador().getNickname());
                    if(c.getItens() != null && !c.getItens().isEmpty()){
                        System.out.println("|-----Itens da Compra:");
                        for(ItensCompra ic : c.getItens())
                            System.out.println("|     > Item: "+ic.getId()+" - Quantidade: "+ic.getQuantidade()+" - Valor: "+ic.getValor()+" - Artefato id: "+ic.getArtefato().getId()+" - Compra id: "+ic.getCompra().getId());
                    }
                    System.out.println("|-----------------------------------------------------------------------------------------------------------------------------------");
                    System.out.println("");
                    System.out.println("");
                }
                int v=1;
                for(Compra rm : listc){
                    conexaoJDBC.remover(rm);
                    System.out.println(">>> Removido compra de id "+rm.getId()+" e todos os itens da respectiva compra");
                    
                }
                System.out.println(">>> Todas as compras e itenscompra foram removidos");
            }else{
                System.out.println(">>>Não encontrou nenhuma compra");
                
                Compra cp = new Compra();
                
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, 2000);
		cal.set(Calendar.MONTH, Calendar.NOVEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 30);
                cp.setData(cal);
                
                cp.setValorTotal(2000.00f);
                
                Jogador j = new Jogador(); 
                j.setNickname("Meed");
                cp.setJogador(j);
                
                conexaoJDBC.persist(cp);
                System.out.println(">>> Compra inserida");
                
                ItensCompra i = new ItensCompra();      
                i.setQuantidade(2);
                i.setValor(1000.00f);
                Artefato a = new Artefato(); 
                a.setId(1);
                i.setArtefato(a);
                i.setCompra(cp);
                conexaoJDBC.persist(i);
                System.out.println(">>> Item inserido");
            }          
            conexaoJDBC.fecharConexao();
         }else{
             System.out.println("Não foi possivel abrir a conexao com DB aberta utilizando JDBC");
         }
    } 
}
