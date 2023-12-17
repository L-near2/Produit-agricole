import javax.swing.*;
import java.awt.*;

public class ContentPanel extends JPanel implements SearchListener{
    private final CardLayout cardLayout;
    JPanel contentPanel;
    ProductGridPanel productGridPanel;
    private String currentPanel;
    private final NavColorChange navColorChangeListener;
    public ContentPanel(String[] labelStrings, NavColorChange navColorChangeListener) {
        this.navColorChangeListener = navColorChangeListener;

        currentPanel = "1";
        SearchPanel searchPanel = new SearchPanel(this);

        cardLayout = new CardLayout();
        setLayout(new BorderLayout());

        contentPanel = new JPanel();
        contentPanel.setLayout(cardLayout);

        ShoppingCartPanel shoppingCartPanel = new ShoppingCartPanel();


        productGridPanel = new ProductGridPanel(shoppingCartPanel);
        contentPanel.add(productGridPanel, Integer.toString(1));

        AddProductPanel addProductPanel = new AddProductPanel(productGridPanel);
        contentPanel.add(addProductPanel, Integer.toString(2));

        contentPanel.add(shoppingCartPanel, Integer.toString(3));


        // Add the remaining panels

        for (int i = 3; i < labelStrings.length; i++){
            JPanel panel = new JPanel();
            panel.setBackground(new Color((int)(Math.random() * 0x1000000)));
            contentPanel.add(panel, Integer.toString(i + 1));
        }

        add(searchPanel, BorderLayout.NORTH);
        add(contentPanel);
    }

    public void showPanel(int index) {
        cardLayout.show(contentPanel, Integer.toString(index));
        currentPanel = Integer.toString(index);
    }

    @Override
    public void onSearch(String text) {
        showPanel(1);
        if(!currentPanel.equals("1") && text.isEmpty()) return;
        navColorChangeListener.changeColor(0);
        productGridPanel.search(text);
    }
}