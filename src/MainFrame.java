import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainFrame extends JFrame implements KeyListener {

    private GamePanel gamePanel;

    public MainFrame() {
        setLocation(560,240);
        setResizable(false);
        setTitle("Flappy Bird");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        /*
        Toolkit tk = Toolkit.getDefaultToolkit();
        for(int i = 1; i < 8; i++) {
            Image icon = tk.getImage(i + "f.png");
            setIconImage(icon);
        }
        */

        addKeyListener(this);
        setFocusable(true);

        setLayout(new BorderLayout());

        gamePanel = new GamePanel();

        add(gamePanel,BorderLayout.CENTER);

        pack();

    }

    public void newG() {
        remove(gamePanel);
        gamePanel = new GamePanel();
        add(gamePanel,BorderLayout.CENTER);
        pack();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        gamePanel.keyPressed(e);
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            newG();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}