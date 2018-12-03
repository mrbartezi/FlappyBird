import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private GamePanel gamePanel;

    public MainFrame() {
        setLocation(560,240);
        setResizable(false);
        setTitle("Flappy Bird");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        setLayout(new BorderLayout());

        gamePanel = new GamePanel();

        add(gamePanel,BorderLayout.CENTER);

        pack();

    }
}
