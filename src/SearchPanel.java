import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SearchPanel extends JPanel {
    SearchListener searchListener;
    public SearchPanel(SearchListener searchListener) {
        this.searchListener = searchListener;

        setLayout(new GridBagLayout());

        GridBagConstraints gbc4 = new GridBagConstraints();
        gbc4.anchor = GridBagConstraints.WEST;

        gbc4.weightx = 1;
        gbc4.insets = new Insets(20, 20, 20, 20);

        JTextField search4 = new JTextField("Search for products...");
        search4.setPreferredSize(new Dimension(300,35));
        Border compoundBorder = new CompoundBorder(
                new MatteBorder(1, 1, 1, 1, new Color(0xF2F2F2)),  // MatteBorder
                new EmptyBorder(5, 20, 5, 0)  // EmptyBorder
        );
        search4.setBorder(compoundBorder);

        search4.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                if(code == KeyEvent.VK_ENTER){
                    searchListener.onSearch(search4.getText());
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        add(search4, gbc4);

        setBorder(new MatteBorder(1, 0, 0, 0, new Color(0xE5E7EB)));
        setBackground(new Color(0xFAFBFB));
    }
}