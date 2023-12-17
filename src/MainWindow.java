import javax.swing.*;
import java.awt.*;

public class MainWindow {

    public MainWindow() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        MainPanel mainPanel = new MainPanel();

        window.add(mainPanel);

        //window.pack();
        window.setSize(new Dimension(800, 800));
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
