import java.util.*;

public class Main {
    public static String[] getConsoleInput() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return input.split("=>");
    }

    public static void createNewProduct(int id, String prodName, int quantity, int price_per_unit, Store store) {
        store.addProduct(id, prodName, price_per_unit, quantity);
        // idProdMap.put(id, newProd);
    }

    public static void parseConsoleInputForAddProduct(String input, Store store) {
        String[] data = input.split("\\|");
        int id = Integer.parseInt(data[0].strip());
        String prodName = data[1].strip();
        int quantity = Integer.parseInt(data[2].strip());
        int price_per_unit = Integer.parseInt(data[3].strip());

        createNewProduct(id, prodName, quantity, price_per_unit, store);
    }


    public static void handleInventory(String input, Store store) {
        parseConsoleInputForAddProduct(input, store);
    }


    public static HashMap<Integer, Integer> parseSaleInput(String input) {
        String[] stringOrderItems = input.strip().split(";");
        HashMap<Integer, Integer> orderItemMap = new HashMap<>();

        for (String x : stringOrderItems) {
            String[] oi = x.strip().split("\\|");
            int id = Integer.parseInt(oi[0]);
            int quantity = Integer.parseInt(oi[1]);

            orderItemMap.put(id, quantity);
        }

        return orderItemMap;
    }


    public static void handleSale(String input, Store store) {
        HashMap<Integer, Integer> orderItemsMap = parseSaleInput(input);

        Order order = store.makeOrder(orderItemsMap);

        order.generateBill();
    }


    public static void handleCheckStock(String input, Store store) {
        int id = Integer.parseInt(input.strip());
        int quantity = store.getStockQuantity(id);
        String name = store.getProductName(id);

        System.out.println(name + " - " + quantity);
    }


    public static void handleInput(String type, String input, Store store) {
        switch (type) {
            case "INVENTORY":
                handleInventory(input, store);
                break;
            case "SALE":
                handleSale(input, store);
                break;
            case "STOCK":
                handleCheckStock(input, store);
                break;
        }
    }

    public static void main(String[] args) {
        Store store = new Store(1, "CASA");
        while (true) {
            String[] typeAndInput = getConsoleInput();
            String type = typeAndInput[0];
            String input = typeAndInput[1];
            handleInput(type, input, store);
        }
    }
}