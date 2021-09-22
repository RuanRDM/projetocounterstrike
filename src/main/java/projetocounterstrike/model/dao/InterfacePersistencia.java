package projetocounterstrike.model.dao;

import java.util.List;
import projetocounterstrike.model.Compra;
import projetocounterstrike.model.Jogador;
/**
 *
 * @author ruan_
 */
public interface InterfacePersistencia {
    public Boolean conexaoAberta();//prot�tipos
    public void fecharConexao();
    public Object find(Class c, Object id) throws Exception;
    public void persist(Object o) throws Exception;
    public void remover(Object o) throws Exception;

    public Jogador doLogin(String nickname, String senha) throws Exception;
    
    public List<Compra> getCompras() throws Exception;

    public List<Jogador> getJogador() throws Exception;
    
}
