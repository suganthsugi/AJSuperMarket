import java.util.*;

public class Inventory {
    HashMap<Integer, Stock> inventory = new HashMap();

    public void addStock(Stock stock) {
        this.inventory.put(stock.product.id, stock);
        System.out.println("Inventory Updated");
    }

    public Stock getStock (int id) {
        return inventory.get(id);
    }

    public Product getProduct(int id) {

        return inventory.get(id).product;
    }

    public void reduceStockForOrder(HashMap<Integer, Integer> orderItemsMap) {
        for (Integer id : orderItemsMap.keySet()) {
            int quantity = orderItemsMap.get(id);
            Stock stock = inventory.get(id);

            stock.updateStock(-quantity);
        }
    }
}
