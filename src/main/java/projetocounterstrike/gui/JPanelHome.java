package projetocounterstrike.gui;
import projetocounterstrike.Controle;
import java.awt.BorderLayout;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
/**
 *
 * @author ruan_
 */
public class JPanelHome extends JPanel{
    private JLabel lblMensagem;
            private JLabel lblData;
            private JLabel lblImagem;
            private BorderLayout layoutGeo;

            private Controle controle;

            public JPanelHome(Controle controle){
                this.controle = controle;
                initComponents();
            }

            private void initComponents(){

                layoutGeo = new BorderLayout();
                this.setLayout(layoutGeo);//seta o gerenciador de layout para este painel.

                lblMensagem = new JLabel("Home - Counter Strike");
                lblMensagem.setHorizontalAlignment(SwingConstants.CENTER);
                this.add(lblMensagem, BorderLayout.NORTH);

                lblImagem = new JLabel(new ImageIcon(JPanelHome.class.getResource("/images/logo_counterstrike.png")));
                this.add(lblImagem, BorderLayout.CENTER);//adiciona a imagem na parte central deste painel.

                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");       

                lblData = new JLabel(df.format(c.getTime()));
                lblData.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                lblData.setHorizontalAlignment(SwingConstants.CENTER);
                this.add(lblData, BorderLayout.SOUTH); //adiciona o rotulo para a data na parte inferior deste painel.       

            }
}
