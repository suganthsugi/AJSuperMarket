import java.util.*;

public class Order {
    List<OrderItem> orderItems = new ArrayList<>();


    public void addOrderItem(int id, int quantity, Store store) {
        Product product = store.getProduct(id);

        if (quantity <= store.getStockQuantity(id)) {
            OrderItem oi = new OrderItem(product, quantity);
            this.orderItems.add(oi);
            store.inventory.getStock(id).updateStock(-quantity);
        }
        else {
            System.out.println(product.id + " | " + product.name + "==> Out of stock");
        }
    }

    public void processOrder(HashMap<Integer, Integer> orderItemsMap, Store store) {
        for (Integer id : orderItemsMap.keySet()) {
            int quantity = orderItemsMap.get(id);
            addOrderItem(id, quantity, store);
        }
    }

    public int getTotal() {
        int total = 0;
        for (OrderItem oi : orderItems) {
            total+=oi.price;
        }

        return total;
    }

    public void generateBill() {
        if(this.orderItems==null) return;

        System.out.println("== BILL ==");
        for (OrderItem oi : this.orderItems) {
            System.out.println(oi.product.id + " - " + oi.product.name + " - " + oi.quantity + " - " + oi.product.price_per_unit + " - " + "N/A" + " - " + oi.quantity*oi.product.price_per_unit);
        }
        System.out.println("== Total ==");
        System.out.println(this.getTotal());
        System.out.println("========");
    }

}
