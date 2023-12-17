import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class ProductCard extends JPanel {
    ProductAddToCartListener productAddToCartListener;

    public ProductCard(Product product, ProductAddToCartListener productAddToCartListener) {
        this.productAddToCartListener = productAddToCartListener;


        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));



        Border compoundBorder = new CompoundBorder(
                new MatteBorder(1, 1, 1, 1, new Color(0xE5E7EB)),  // MatteBorder
                new EmptyBorder(10, 10, 50, 10)  // EmptyBorder
        );
        setBorder(compoundBorder);

        setBackground(new Color(0XF3F4F6));

        String productText = "<html><h2><center><b>" + product.getTitle() + "</b></center></h2><br><b>Type:</b> " + product.getType() + "<br><b>Price:</b> " + product.getPrice() + "</html>";

        BufferedImage myPicture;
        try {
            myPicture = ImageIO.read(new File(product.getImage()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Image newPicture = myPicture.getScaledInstance(228, 187, Image.SCALE_SMOOTH);

        JLabel productImage = new JLabel(new ImageIcon(newPicture));
        productImage.setHorizontalAlignment(SwingConstants.CENTER);
        productImage.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel label = new JLabel(productText, SwingConstants.CENTER);
        Font font = label.getFont().deriveFont(Font.PLAIN);
        label.setFont(font);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btn = getButton(product, productAddToCartListener);

        JButton btn2 = getBtn2(product);

        add(productImage);
        add(Box.createVerticalStrut(50));
        add(label);
        add(Box.createVerticalStrut(10));
        add(btn);
        add(Box.createVerticalStrut(10));
        add(btn2);
        add(Box.createVerticalStrut(30));
        setPreferredSize(new Dimension(250, calculatePreferredHeight()));
    }

    private static JButton getBtn2(Product product) {
        JButton btn2 = new JButton("More details");
        btn2.setToolTipText("Click for more details");
        btn2.setFocusPainted(false);
        btn2.setBorder(new EmptyBorder(8, 40, 8, 40));
        btn2.setBackground(new Color(0xCCCCCC ));
        btn2.setForeground(new Color(0x000000));
        btn2.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProductDetailPanel(product);
            }
        });
        return btn2;
    }

    private static JButton getButton(Product product, ProductAddToCartListener productAddToCartListener) {
        JButton btn = new JButton("Add to cart");
        btn.setToolTipText("Click to add the item to your cart");
        btn.setFocusPainted(false);
        btn.setBorder(new EmptyBorder(8, 40, 8, 40));
        btn.setBackground(new Color(0x18181B));
        btn.setForeground(new Color(0xF4F4F4));
        btn .setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productAddToCartListener.onProductAddedToCart(product);
            }
        });
        return btn;
    }

    private int calculatePreferredHeight() {
        int sum = 0;
        for (Component component : getComponents()) {
            sum += component.getPreferredSize().height;
        }
        return sum;
    }
}