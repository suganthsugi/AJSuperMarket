import java.util.*;

public class Store {
    int id;
    String name;
    Inventory inventory;

    List<Order> orders = new ArrayList<>();


    Store(int id, String name) {
        this.id = id;
        this.name = name;
        this.inventory = new Inventory();
    }

    public void addProduct(int id, String name, int price_per_quantity, int quantity) {
        Product newProduct = new Product(id, name, price_per_quantity);
        Stock newStock = new Stock(newProduct, quantity);
        inventory.addStock(newStock);
    }

    public Product getProduct(int id) {
        return this.inventory.getProduct(id);
    }


    public int getStockQuantity(int id) {
        return this.inventory.getStock(id).quantity;
    }


    public String getProductName(int id) {
        return this.inventory.getProduct(id).name;
    }

    public Order makeOrder(HashMap<Integer, Integer> orderItemsMap) {
        Order newOrder = new Order();

        newOrder.processOrder(orderItemsMap, this);

        if (newOrder.orderItems != null){
            this.orders.add(newOrder);
        }

        return newOrder;
    }
}
