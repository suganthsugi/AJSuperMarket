public class Stock {
    Product product;
    int quantity;

    Stock(Product p, int quantity) {
        this.product = p;
        this.quantity = quantity;
    }


    void updateStock(int quantity) {
        this.quantity+=quantity;
    }
}
