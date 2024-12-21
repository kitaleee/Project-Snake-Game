import javax.swing.JFrame;
public class GameFrame extends JFrame {
       GameFrame() {
           this.setTitle("Snake Game");
           this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           this.setResizable(false);
           this.setContentPane(new MenuPanel(this));
           this.pack();
           this.setVisible(true);
           this.setLocationRelativeTo(null);
       }
   }
   
