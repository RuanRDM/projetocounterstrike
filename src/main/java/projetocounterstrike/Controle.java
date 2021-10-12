package projetocounterstrike;
import projetocounterstrike.gui.JFramePrincipal;
import projetocounterstrike.gui.JMenuBarHome;
import projetocounterstrike.gui.JPanelHome;
import projetocounterstrike.gui.autenticacao.JPanelAutenticacao;
import projetocounterstrike.gui.usuario.JPanelCompra;
import projetocounterstrike.model.Jogador;
import projetocounterstrike.model.dao.PersistenciaJDBC;
import javax.swing.JOptionPane;
/**
 *
 * @author ruan_
 */
public class Controle {
    
            private PersistenciaJDBC conexaoJDBC;
            private JFramePrincipal frame; // frame principal da minha aplicação gráfica
            private JPanelAutenticacao pnlAutenticacao; //painel para a autenticacao do Jogador.  
            private JMenuBarHome menuBar; //menu principal    
            private JPanelHome pnlHome; // paine de boas vindas (home)                
            private JPanelCompra pnlCompra;  // tela de CRUD para Compras.
            public Controle(){
                        
            }
            
            public boolean conectarBD() throws Exception {
                conexaoJDBC = new PersistenciaJDBC();
                if(conexaoJDBC!= null){
                    return conexaoJDBC.conexaoAberta();
                }
                return false;
            }
    
            public void fecharBD(){
                System.out.println("Fechando conexao com o Banco de Dados");
                conexaoJDBC.fecharConexao();
            }
            
            public void initComponents(){
                frame = new JFramePrincipal(this);      
                        
                pnlAutenticacao = new JPanelAutenticacao(this);
                menuBar = new JMenuBarHome(this);
                pnlHome = new JPanelHome(this);
                pnlCompra = new JPanelCompra(this);
                        
                frame.addTela(pnlAutenticacao, "tela_autenticacao");//carta 1
                frame.addTela(pnlHome, "tela_home");//carta 2
                frame.addTela(pnlCompra, "tela_compra");//carta 3
                        
                frame.showTela("tela_autenticacao"); // apreseta a carta cujo nome é "tela_autenticacao"
                        
                frame.setVisible(true); // torna visível o jframe
            }
           
            
            public void autenticar(String nickname, String senha) { 
                try{
                    Jogador u =  getConexaoJDBC().doLogin(nickname, senha);
                    if(u != null){
                        JOptionPane.showMessageDialog(pnlAutenticacao, "Jogador "+u.getNickname()+" autenticado com sucesso!", "Autenticação", JOptionPane.INFORMATION_MESSAGE);

                        frame.setJMenuBar(menuBar);//adiciona o menu de barra no frame
                        frame.showTela("tela_home");//muda a tela para o painel de boas vindas (home)
                    }else{
                        JOptionPane.showMessageDialog(pnlAutenticacao, "Dados inválidos!", "Autenticação", JOptionPane.INFORMATION_MESSAGE);
                    }
                }catch(Exception e){
                    JOptionPane.showMessageDialog(pnlAutenticacao, "Erro ao executar a autenticação no Banco de Dados!", "Autenticação", JOptionPane.ERROR_MESSAGE);
                }
            }
            
            public void showTela(String nomeTela){        
                if(nomeTela.equals("tela_compra")){
                    pnlCompra.getTelaListagem().populaTable();
                }
                frame.showTela(nomeTela);
            }
            
            public PersistenciaJDBC getConexaoJDBC() {
                return conexaoJDBC;
            }
    
}
