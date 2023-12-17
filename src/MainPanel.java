import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel implements NavColorChange{
    NavigationPanel navigationPanel;
    public MainPanel() {
        setLayout(new BorderLayout());

        //String[] labelStrings = {"Inbox", "Sent", "Archive", "Trash", "Drafts", "Spam", "Important"};
        String[] labelStrings = {"Products List", "Add Product", "Shopping Cart", "Trash", "Drafts", "Spam", "EXIT"};

        ContentPanel contentPanel = new ContentPanel(labelStrings, this);

        navigationPanel = new NavigationPanel(labelStrings, contentPanel::showPanel);

        add(navigationPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
    }

    @Override
    public void changeColor(int index) {
        navigationPanel.changeColor(index);
    }
}