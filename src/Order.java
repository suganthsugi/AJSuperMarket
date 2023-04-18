import java.util.*;

public class Order {
    List<OrderItem> orderItems = new ArrayList<>();
    public void processOrder(HashMap<Integer, Integer> orderItemsMap, Store store) {
        for (Integer id : orderItemsMap.keySet()) {
            Product product = store.getProduct(id);
            int quantity = orderItemsMap.get(id);

            if(quantity <= store.getStockQuantity(id)) {
                OrderItem oi = new OrderItem(product, quantity);
                orderItems.add(oi);
            }
            else {
                System.out.println(product.id + " | " + product.name + "==> Out of stock");
                this.orderItems = null;
                return;
            }
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
