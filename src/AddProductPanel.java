import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class AddProductPanel extends JPanel {
    private String iconFilePath = null;
    JTextField productNameField;
    JTextField productTypeField;
    JTextField productPriceField;
    JTextArea productDescriptionField;
    JLabel productImage;

    public AddProductPanel(ProductAddedListener productAddedListener) {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        CompoundBorder compoundBorder2 = new CompoundBorder(
                new MatteBorder(1, 1, 1, 1, new Color(0xE5E7EB)),
                new EmptyBorder(15, 10, 15, 10)
        );

        JPanel panel = new JPanel();
        panel.setBackground(null);
        CompoundBorder compoundBorder = new CompoundBorder(
                new MatteBorder(1, 1, 1, 1, new Color(0xE5E7EB)),
                new EmptyBorder(40, 80, 40, 80)
        );

        panel.setBorder(compoundBorder);

        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.anchor = GridBagConstraints.WEST;
        gbc2.weighty = 0;
        gbc2.weightx = 1;
        gbc2.insets = new Insets(0,30, 20, 30);
        gbc2.gridx = 0;

        Font font = new Font("Arial", Font.BOLD, 24);
        panel.setFont(font);

        JLabel productName = new JLabel("Product Name:");
        productName.setFont(font);
        productName.setForeground(new Color(0x394353));
        productNameField = new JTextField();
        productNameField.setBorder(compoundBorder2);

        gbc2.gridy = 0;
        panel.add(productName, gbc2);
        gbc2.gridy = 1;
        panel.add(productNameField, gbc2);

        JLabel productType = new JLabel("Product type:");
        productType.setFont(font);
        productType.setForeground(new Color(0x394353));
        productTypeField = new JTextField();
        productTypeField.setBorder(compoundBorder2);

        gbc2.gridy = 2;
        panel.add(productType, gbc2);
        gbc2.gridy = 3;
        panel.add(productTypeField, gbc2);

        JLabel productPrice = new JLabel("Product Price:");
        productPrice.setFont(font);
        productPrice.setForeground(new Color(0x394353));
        productPriceField = new JTextField();
        productPriceField.setBorder(compoundBorder2);

        gbc2.gridy = 4;
        panel.add(productPrice, gbc2);
        gbc2.gridy = 5;
        panel.add(productPriceField, gbc2);

        JLabel productDescription = new JLabel("Product Description:");
        productDescription.setFont(font);
        productDescription.setForeground(new Color(0x394353));
        productDescriptionField = new JTextArea(10, 25);
        productDescriptionField.setBorder(compoundBorder2);

        gbc2.gridy = 6;
        panel.add(productDescription, gbc2);
        gbc2.gridy = 7;
        panel.add(productDescriptionField, gbc2);

        gbc2.gridy = 8;

        productImage = new JLabel();
        productImage.setVisible(false);
        panel.add(productImage, gbc2);

        JButton btn = getButton(productAddedListener);
        gbc2.gridy = 9;

        gbc2.fill = GridBagConstraints.NONE;
        gbc2.gridwidth = 1;
        panel.add(btn, gbc2);

        JButton btn2 = getBtn2();
        gbc2.gridy = 10;

        gbc2.fill = GridBagConstraints.NONE;
        gbc2.gridwidth = 1;
        panel.add(btn2, gbc2);

        JScrollPane scrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setViewportView(panel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null);
        scrollPane.setBackground(null);
        panel.setBackground(Color.WHITE);

        add(scrollPane);

    }

    private JButton getBtn2() {
        JButton btn2 = new JButton("Choose Image");
        btn2.setToolTipText("Click to choose an image for the product");

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif", "png");
                fileChooser.setFileFilter(filter);

                int returnValue = fileChooser.showOpenDialog(null);

                if(returnValue == JFileChooser.APPROVE_OPTION){
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        BufferedImage myPicture = ImageIO.read(new File(selectedFile.getPath()));
                        Image newImage = myPicture.getScaledInstance(356, -1, Image.SCALE_SMOOTH);
                        productImage.setIcon(new ImageIcon(newImage));
                        productImage.setVisible(true);
                        iconFilePath = selectedFile.getPath();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error loading the image");
                    }
                }
            }
        });
        btn2.setFocusPainted(false);
        btn2.setBorder(new EmptyBorder(8, 40, 8, 40));
        btn2.setBackground(new Color(0x18181B));
        btn2.setForeground(new Color(0xF4F4F4));
        btn2.setAlignmentX(Component.CENTER_ALIGNMENT);
        return btn2;
    }

    private JButton getButton(ProductAddedListener productAddedListener) {
        JButton btn = new JButton(" Add ");
        btn.setToolTipText("Click to add a product to the list");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String productName = productNameField.getText();
                String productType = productTypeField.getText();
                String productDescription = productDescriptionField.getText();

                double productPrice;
                try {
                    productPrice = Double.parseDouble(productPriceField.getText());
                } catch (Exception exceptionError) {
                    productPrice = 0;
                }

                if(productName.isEmpty() || productType.isEmpty() || productDescription.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Please fill all the fields");
                    return;
                }

                if(productPrice <= 0){
                    JOptionPane.showMessageDialog(null, "Please enter a valid price");
                    return;
                }

                if(iconFilePath == null || iconFilePath.isEmpty()){
                    iconFilePath = "images.jpg";
                }
                Product product = new Product(iconFilePath, productName, productType, productPrice, productDescription);
                productAddedListener.onProductAdded(product);
                resetFields();
            }
        });
        btn.setFocusPainted(false);
        btn.setBorder(new EmptyBorder(8, 40, 8, 40));
        btn.setBackground(new Color(0x18181B));
        btn.setForeground(new Color(0xF4F4F4));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        return btn;
    }

    private void resetFields() {
        productNameField.setText("");
        productTypeField.setText("");
        productPriceField.setText("");
        productDescriptionField.setText("");
        productImage.setVisible(false);
        iconFilePath = null;
    }
}