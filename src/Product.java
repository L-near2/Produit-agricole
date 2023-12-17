public class Product {
    private final String image;
    private final String title;
    private final String type;
    private final double price;
    private final String description;


    public Product(String image, String title, String type, double price, String description) {
        this.image = image;
        this.title = title;
        this.type = type;
        this.price = price;
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return Objects.equals(title, product.title) && Objects.equals(type, product.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, type);
    }*/

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return Double.compare(price, product.price) == 0 && Objects.equals(image, product.image) && Objects.equals(title, product.title) && Objects.equals(type, product.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(image, title, type, price);
    }*/

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}