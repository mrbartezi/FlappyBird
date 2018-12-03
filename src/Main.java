import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try
        {
            for(UIManager.LookAndFeelInfo info:UIManager.getInstalledLookAndFeels()) {
                if(info.getName() == "Windows") {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch(Exception e) {
        }

        SwingUtilities.invokeLater(() -> new MainFrame());
    }
}