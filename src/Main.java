import java.util.*;

public class Main {
    public static Inventory inventory = new Inventory();
    public static HashMap<Integer, Product> idProdMap = new HashMap<>();
    public static HashMap<Product, Stock> prodStockMap = new HashMap<>();
    public static List<Order> orders = new ArrayList<>();

    public static String[] getInput() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] typeAndInput = input.split("=>");
        return typeAndInput;
    }

    public static Product createNewProduct(String input) {
        String[] data = input.split("\\|");
        int id = Integer.parseInt(data[0].strip());
        String prodName = data[1].strip();
        int price_per_unit = Integer.parseInt(data[3].strip());

        Product newProd = new Product(id, prodName, price_per_unit);
        idProdMap.put(id, newProd);
        return newProd;
    }

    public static Stock createNewStock(Product newProd, String input) {
        String[] data = input.split("\\|");
        int quantity = Integer.parseInt(data[2].strip());
        Stock newStock = new Stock(newProd, quantity);
        prodStockMap.put(newProd, newStock);
        return newStock;
    }

    public static void handleInventory(String input) {
        Product newProd = createNewProduct(input);
        Stock newStock = createNewStock(newProd, input);
        inventory.addStock(newStock);
    }

    public static String[] getItemsFromInput(String input) {
        return input.strip().split(";");
    }

    public static HashMap<String, Integer> getOrderItemDataFromInput(String input) {
        String[] oiData = input.strip().split("\\|");
        int prodId = Integer.parseInt(oiData[0].strip());
        int quantity = Integer.parseInt(oiData[1].strip());
        HashMap<String, Integer> itemData = new HashMap<>();
        itemData.put("prodId", prodId);
        itemData.put("quantity", quantity);

        return itemData;
    }

    public static void updateStockQuantity(HashMap<Stock, Integer> purchasedQuantity){
        for (Map.Entry<Stock, Integer> entry : purchasedQuantity.entrySet()) {
            Stock stock = entry.getKey();
            int quantity = entry.getValue();
            stock.updateStock(-quantity);
        }
    }

    public static OrderItem[] createOrderItems(String input) {
        String[] items = getItemsFromInput(input);
        int totItems = items.length;

        OrderItem[] orderItems = new OrderItem[totItems];
        HashMap<Stock, Integer> purchasedQuantity = new HashMap<>();

        for(int i=0; i<totItems; i++) {
            HashMap<String, Integer> itemData = getOrderItemDataFromInput(items[i]);
            int id = itemData.get("prodId");
            int quantity = itemData.get("quantity");
            Product currProd = idProdMap.get(id);
            Stock currStock = prodStockMap.get(currProd);

            if(currStock.ckeckForAvailability((quantity))){
                OrderItem oi = new OrderItem(currProd, quantity);
                orderItems[i] = oi;
                purchasedQuantity.put(currStock, quantity);
            }
            else{
                System.out.println("Order for "+currProd.name+" is not available => Available quantity : "+currStock.quantity);
                return null;
            }
        }

        updateStockQuantity(purchasedQuantity);
        return orderItems;

    }

    public static Order createOrder(OrderItem[] orderItems) {
        Order order = new Order(orderItems);
        order.calculateTotal();
        return order;
    }

    public static void handleSale(String input) {
        OrderItem[] orderItems = createOrderItems(input);
        if(orderItems==null){
            System.out.println("Order aborted");
            return;
        }
        Order order = createOrder(orderItems);
        orders.add(order);
        order.gendrateBill();
    }

    public static int getStockById(int id) {
        Product currProd = idProdMap.get(id);
        Stock currStock = prodStockMap.get(currProd);

        return  currStock.quantity;
    }

    public static void handleCheckStock(String input) {
        int id = Integer.parseInt(input.strip());
        int stock = getStockById(id);
        String prodName = idProdMap.get(id).name;
        System.out.println(prodName + " - " + stock);
    }

    public static void handleInput(String type, String input) {
        if(type.equalsIgnoreCase("INVENTORY")) {
            handleInventory(input);
        }
        else if(type.equalsIgnoreCase("SALE")) {
            handleSale(input);
        }
        else if(type.equalsIgnoreCase("STOCK")) {
            handleCheckStock(input);
        }
    }

    public static void main(String[] args) {
        while (true) {
            String[] typeAndInput = getInput();
            String type = typeAndInput[0];
            String input = typeAndInput[1];
            handleInput(type, input);
        }
    }
}