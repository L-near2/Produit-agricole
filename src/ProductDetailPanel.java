import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ProductDetailPanel extends JDialog {

    public ProductDetailPanel(Product product) {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModalityType(ModalityType.APPLICATION_MODAL);

        CompoundBorder compoundBorder = new CompoundBorder(
                new MatteBorder(0, 0, 1, 0, new Color(0x000000)),
                new EmptyBorder(20, 20, 20, 20)
        );

        JLabel title = new JLabel();
        title.setText("Title : " + product.getTitle());
        title.setForeground(Color.BLACK);
        title.setFont(new Font("MV Bola",Font. ITALIC, 15));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(compoundBorder);


        JLabel type = new JLabel();
        type.setText("Type : " + product.getType());
        type.setForeground(Color.BLACK);
        type.setFont(new Font("Arial",Font.BOLD , 15));
        type.setAlignmentX(Component.CENTER_ALIGNMENT);
        type.setBorder(compoundBorder);


        JLabel price = new JLabel();
        price.setText("Price : " + product.getPrice());
        price.setForeground(Color.BLACK);
        price.setFont(new Font("Arial",Font.BOLD , 15));
        price.setAlignmentX(Component.CENTER_ALIGNMENT);
        price.setBorder(compoundBorder);


        JLabel description = new JLabel();
        description.setText("<html><b>description : <b>" + product.getDescription() + "</html>");
        description.setPreferredSize(new Dimension(50, -1));
        description.setForeground(Color.BLACK);

        description.setFont(new Font("Arial",Font.BOLD , 15));
        description.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension (575,-1));
        leftPanel.setBackground(Color.white);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));


        BufferedImage myPicture;
        try {
            myPicture = ImageIO.read(new File(product.getImage()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Image newPicture = myPicture.getScaledInstance(356, 178, Image.SCALE_SMOOTH);
        JLabel labelImg = new JLabel(new ImageIcon(newPicture));
        labelImg.setBounds(50, 50, 500, 500);


        JButton button = getButton();

        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension (600,1000));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(null);

        setLayout(new BorderLayout());

        getContentPane().setBackground(new Color(255,255,255));

        leftPanel.add(title);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        leftPanel.add(type);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        leftPanel.add(price);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        leftPanel.add(description);

        rightPanel.add(labelImg);
        rightPanel.add(button);

        JScrollPane scrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setViewportView(leftPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null);

        add(scrollPane,BorderLayout.WEST);
        add(rightPanel,BorderLayout.EAST);


        setResizable(false);
        setTitle("Product Detail");
        setSize(1150, 650);
        setLocationRelativeTo(this);
        setVisible(true);
    }

    private JButton getButton() {
        JButton button = new JButton();
        button.setToolTipText("Click to close window");
        button.setText("Close");
        button.setBorder(new EmptyBorder(8, 5, 8, 5));
        button.setFocusPainted(false);
        button.setFocusable(false);
        button.setBackground(new Color(0x18181B));
        button.setForeground(new Color(0xF4F4F4));
        button.setFont(new Font ("Comic Sans",Font.BOLD,15));
        button.setBounds(100, 510, 170, 50);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
        return button;
    }
}
