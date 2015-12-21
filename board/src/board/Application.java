package board;

import java.awt.EventQueue;
import javax.swing.JFrame;


public class Application extends JFrame {
    
    public Application() {

        initUI();
    }

    private void initUI() {

        add(new Board());

        setSize(500, 400);

        setTitle("Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // center window on the screen
    }    
    
    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Application ex = new Application();
                ex.setVisible(true);
            }
        });
    }
}