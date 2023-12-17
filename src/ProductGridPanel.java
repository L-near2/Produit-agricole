import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class ProductGridPanel extends JPanel implements ProductAddedListener{
    Set<Product> products;
    JPanel productList;
    JPanel currentRow;
    int i = 0;
    JScrollPane scrollPane;
    ShoppingCartPanel shoppingCartPanel;
    public ProductGridPanel(ShoppingCartPanel shoppingCartPanel) {
        this.shoppingCartPanel = shoppingCartPanel;
        products = new HashSet<>();
        products.add(new Product("fraise3.jpg", "Grain", "Wheat", 500, "Some Desciption"));
        products.add(new Product("fraise3.jpg", "Grain", "Wheat", 500, "Some Desciption"));
        products.add(new Product("fraise3.jpg", "Grain", "Wheat", 500, "Some Desciption"));
        products.add(new Product("fraise3.jpg", "Grain", "Wheat", 500, "Some Desciption"));
        products.add(new Product("fraise3.jpg", "Grain", "Wheat", 500, "Some Desciption"));
        products.add(new Product("fraise3.jpg", "Grain", "Wheat", 500, "Some Desciption"));
        products.add(new Product("fraise3.jpg", "Grain", "Wheat", 500, "Some Desciption"));
        products.add(new Product("fraise3.jpg", "Grain", "Wheat", 500, "Some Desciption"));
        products.add(new Product("fraise3.jpg", "Grain", "Wheat", 500, "Some Desciption"));

        if (products.contains(new Product("fraise3.jpg", "Grain", "Wheat", 500, "Some Desciption"))) {
            System.out.println("contains");
        } else {
            System.out.println("not contains");
        }


        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        productList = new JPanel(new GridLayout(0, 1));
        productList.setBackground(Color.WHITE);


        currentRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));

        for (Product product : products) {
            if (i % 4 == 0) {
                currentRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
                currentRow.setBackground(null);
                productList.add(currentRow);
            }

            currentRow.add(new ProductCard(product, shoppingCartPanel));
            i++;
        }

        Font font = new Font("Arial", Font.BOLD, 24);

        scrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setViewportView(productList);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null);

        add(scrollPane);
    }

    @Override
    public void onProductAdded(Product product) {
        products.add(product);
        products.forEach(System.out::println);

        if (i % 4 == 0) {
            currentRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
            currentRow.setBackground(null);
            productList.add(currentRow);
        }

        ProductCard newProductCard = new ProductCard(product, shoppingCartPanel);
        currentRow.add(newProductCard);
        i++;
        System.out.println("i = " + i);

        revalidate();
        repaint();
    }

    public void search(String query) {


        if(query.isEmpty() && productList.getComponentCount() == products.size()) {
            return;
        } else if (query.isEmpty() && (productList.getComponentCount() == 0 || productList.getComponentCount() != products.size()) ) {
            productList.removeAll();
            i = 0;

            for (Product product : products) {
                if (i % 4 == 0) {
                    currentRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
                    currentRow.setBackground(null);
                    productList.add(currentRow);
                }

                currentRow.add(new ProductCard(product, shoppingCartPanel));
                i++;
            }
            revalidate();
            repaint();
            return;
        }

        productList.removeAll();
        i = 0;

        for (Product product : products) {
            if (product.getTitle().toLowerCase().contains(query.toLowerCase())) {
                if (i % 4 == 0) {
                    currentRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
                    currentRow.setBackground(null);
                    productList.add(currentRow);
                }
                currentRow.add(new ProductCard(product, shoppingCartPanel));
                i++;
            }
        }
        revalidate();
        repaint();
    }
}