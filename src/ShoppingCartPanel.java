import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ShoppingCartPanel extends JPanel implements ProductAddToCartListener{
    Set<Product> products;
    JPanel cartPanel;
    GridBagConstraints gbc;

    public ShoppingCartPanel() {
        products = new HashSet<>();
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        cartPanel = new JPanel();
        cartPanel.setBackground(Color.WHITE);
        cartPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;

        gbc.weightx = 1;
        gbc.insets = new Insets(10, 10, 10, 10);


        cartPanel.setBorder(new EmptyBorder(40, 30, 40, 30));

        JScrollPane scrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setViewportView(cartPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null);

        add(scrollPane);

    }

    @Override
    public void onProductAddedToCart(Product product) {
        System.out.println("Product added to cart");

        JPanel productPanel = new JPanel(new BorderLayout());
        BufferedImage myPicture;
        try {
            myPicture = ImageIO.read(new File(product.getImage()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image newImage = myPicture.getScaledInstance(356, 187, Image.SCALE_SMOOTH);
        JLabel productImage = new JLabel(new ImageIcon(newImage));
        productImage.setBorder(new MatteBorder(0, 0, 0, 1, new Color(0xF2F2F2)));
        productPanel.add(productImage, BorderLayout.WEST);

        JPanel productDescriptionPanel = new JPanel(new GridBagLayout());


        productDescriptionPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("<html> <h2>" + product.getTitle() +  "</h2><br> <p>Price: $" + product.getPrice() + "</p><br><p>Type: " + product.getType() + "</p>  <br> <p>" + product.getDescription() + "</p></html>");
        title.setMaximumSize(new Dimension(187, 187));
        title.setFont(new Font("Arial", Font.PLAIN, 16));
        title.setForeground(new Color(0x000000));

        JButton btn = getButton(product, productPanel);


        gbc.gridy = 0;
        productDescriptionPanel.add(title, gbc);
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        productDescriptionPanel.add(btn, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        productDescriptionPanel.setBackground(null);

        productPanel.add(productDescriptionPanel, BorderLayout.CENTER);

        productPanel.setBorder(new MatteBorder(1, 1, 3, 3, new Color(0xF2F2F2)));
        productPanel.setBackground(null);

        gbc.gridy = products.size();
        System.out.println("Products size: " + (products.size()+1));
        cartPanel.add(productPanel, gbc);

        products.add(product);
    }

    private JButton getButton(Product product, JPanel productPanel) {
        JButton btn = new JButton("Remove");
        btn.setToolTipText("Click to remove the item");
        btn.setFocusPainted(false);
        btn.setBorder(new EmptyBorder(8, 40, 8, 40));
        btn.setBackground(new Color(0xFF4136));
        btn.setForeground(new Color(0xFFFFFF ));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this item?");
                // 0=yes, 1=no, 2=cancel

                if(input == 0) {
                    products.remove(product);
                    cartPanel.remove(productPanel);
                    revalidate();
                    repaint();
                }
            }
        });
        return btn;
    }
}