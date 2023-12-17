import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NavigationPanel extends JPanel{
    private final JLabel[] labels;

    public NavigationPanel(String[] labelStrings, PanelChangeListener listener) {
        this.labels = new JLabel[labelStrings.length];
        init(labelStrings, listener);
    }

    private void addLabelMouseListener(JLabel label, int index, PanelChangeListener listener) {
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JLabel clickedLabel = (JLabel) e.getSource();

                for (Component component : getComponents()) {
                    if (component instanceof JLabel) {
                        component.setForeground(new Color(0x9197A1));
                    }
                }

                clickedLabel.setForeground(new Color(0x111827));

                listener.onPanelChange(index + 1);
            }
        });
    }

    public void changeColor(int index) {
        for (Component component : getComponents()) {
            if (component instanceof JLabel) {
                //component.setForeground(Color.WHITE);
                component.setForeground(new Color(0x9197A1));
            }
        }
        labels[index].setForeground(new Color(0x111827));
    }

    private void init(String[] labelStrings, PanelChangeListener listener) {
        setLayout(new GridBagLayout());
        setBackground(new Color(0xFAFBFB));
        setBorder(new MatteBorder(1, 1, 1, 1, new Color(0xE5E7EB)));
        setPreferredSize(new Dimension(250, 100));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15,5,15,5);

        Font font = new Font("Arial", Font.BOLD, 25);
        Color color = new Color(0x9197A1);

        for (int i = 0; i < labels.length; i++) {
            gbc.gridy = i;
            labels[i] = new JLabel(labelStrings[i]);
            labels[i].setFont(font);
            labels[i].setForeground(color);
            add(labels[i], gbc);
            addLabelMouseListener(labels[i], i, listener);
        }

        labels[0].setForeground(new Color(0x111827));
    }
}