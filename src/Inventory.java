import java.util.*;

public class Inventory {
    HashMap<Integer, Stock> inventory = new HashMap();

    public void addStock(Stock stock) {
        this.inventory.put(stock.product.id, stock);
        System.out.println("Inventory Updated");
    }
}
