/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetocounterstrike.test;

import org.junit.Test;
import projetocounterstrike.model.dao.PersistenciaJPA;

/**
 *
 * @author ruan_
 */
public class TestePersistenciaJPA {
    
    //@Test
    public void testarConexao() throws Exception{
        PersistenciaJPA persistencia = new PersistenciaJPA();
        
        if(persistencia.conexaoAberta()){
            System.out.println("Conexao com DB aberta utilizando JPA");
            persistencia.fecharConexao();
        }else{
            System.out.println("Não foi possivel abrir a conexao com DB aberta utilizando JPA");
        }
    }
}
