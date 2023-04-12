public class Order {
    OrderItem[] orderItems;
    int total;

    Order(OrderItem[] orderItems) {
        this.orderItems = orderItems;
        this.total = 0;
    }

    void calculateTotal() {
        int total = 0;
        for (int i=0; i<this.orderItems.length; i++) {
            total+=orderItems[i].price;
        }
        this.total = total;
    }

    void gendrateBill() {
        System.out.println("== Bill ==");
        for (OrderItem oi : this.orderItems) {
            System.out.println(oi.product.id + "|" + oi.product.name + "|" + oi.quantity + "|" + "N/A" + "|" + oi.price);
        }
        System.out.println("== Total ==");
        System.out.println(this.total);
        System.out.println("========");
    }
}
