public class OrderItem {
    Product product;
    int quantity;
    int price;

    OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.price = product.price_per_unit * quantity;
    }
}
