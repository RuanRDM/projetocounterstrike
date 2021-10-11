package projetocounterstrike.gui.autenticacao;
import projetocounterstrike.Controle;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
/**
 *
 * @author ruan_
 */
public class JPanelAutenticacao  extends JPanel implements ActionListener{
    
    private Controle controle;
    private GridBagLayout gridLayout;
    private GridBagConstraints posicionador;
    
    private JLabel lblnick;
    private JLabel lblSenha;
    private JTextField txfnick;
    private JPasswordField psfSenha;
    private JButton btnLogar;

    
    public JPanelAutenticacao(Controle controle){
        
        this.controle = controle;
        initComponents();
    }
    
    private void initComponents(){
    
        gridLayout = new GridBagLayout();//inicializando o gerenciador de layout
        this.setLayout(gridLayout);//definie o gerenciador para este painel.
        
        lblnick = new JLabel("Nickname: ");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 0;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        this.add(lblnick, posicionador);//o add adiciona o rotulo no painel
        
        txfnick = new JTextField(10);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 0;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        this.add(txfnick, posicionador);//o add adiciona o rotulo no painel        
        
        lblSenha = new JLabel("Senha: ");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        this.add(lblSenha, posicionador);//o add adiciona o rotulo no painel
        
        psfSenha = new JPasswordField(10);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        this.add(psfSenha, posicionador);//o add adiciona o rotulo no painel  

        btnLogar = new JButton("Autenticar");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 2;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        btnLogar.addActionListener(this);//registrar o botão no Listener
        btnLogar.setActionCommand("comando_autenticar");
        this.add(btnLogar, posicionador);//o add adiciona o rotulo no painel

    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getActionCommand().equals(btnLogar.getActionCommand())){
            
            //validacao do formulario.
            if(txfnick.getText().trim().length() > 3 && new String(psfSenha.getPassword()).trim().length() > 3 ){                       
                controle.autenticar(txfnick.getText().trim(), new String(psfSenha.getPassword()).trim());  
            }else{
                JOptionPane.showMessageDialog(this, "Informe os dados para Nickname e Senha! (Somente são aceitos nicknames e senhas com mais de 3 caracteres)", "Autenticação", JOptionPane.ERROR_MESSAGE);
            }   
        }                
        
    } 
    
}