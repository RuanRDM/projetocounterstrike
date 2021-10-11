/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetocounterstrike.gui.usuario;
import projetocounterstrike.Controle;
import java.awt.CardLayout;
import javax.swing.JPanel;
/**
 *
 * @author ruan_
 */
public class JPanelUsuario extends JPanel{
    
            private Controle controle;
            private CardLayout layoutCard;
    
            private JPanelListagem telaListagem;
            private JPanelEdicao telaEdicao;
            
            public JPanelUsuario(Controle controle){
                this.controle = controle;
                initComponents();
            }
            
                private void initComponents(){
        
                        layoutCard = new CardLayout();//inicializa o gerenciador de layout.
                        this.setLayout(layoutCard);//defini o gerenciador de layout para este painel.

                        telaListagem = new JPanelListagem(this, getControle());
                        
                        telaEdicao = new JPanelEdicao(this, getControle());

                        this.add(getTelaListagem(), "tela_listagem"); // adiciona uma carta
                        this.add(getTelaFormulario(), "tela_edicao"); // adiciona a segunda carta no baralho.        

                        layoutCard.show(this, "tela_listagem"); //por padrão mostra o painel de listagem
           }
                
            /*public void showTela(String nomeTela){

                        if(nomeTela.equals("tela_edicao")){

                            getTelaFormulario().populaComboCidade();

                        }else if(nomeTela.equals("tela_listagem")){

                            telaListagem.populaTable();
                        }

                        layoutCard.show(this, nomeTela); 
            }*/    
                
            public JPanelEdicao getTelaFormulario() {
                return telaEdicao;
            }
            
            public JPanelListagem getTelaListagem() {
                return telaListagem;
            }  
            
            public Controle getControle() {
                return controle;
            }    
    
}
