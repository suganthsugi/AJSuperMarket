import java.util.*;

public class Main {
    public static Inventory inventory = new Inventory();
    public static HashMap<Integer, Product> idProdMap = new HashMap<>();
    public static HashMap<Product, Stock> prodStockMap = new HashMap<>();

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

    public static void handleInput(String type, String input) {
        if(type.equalsIgnoreCase("INVENTORY")) {
            handleInventory(input);
        }
    }

    public static void main(String[] args) {
        String[] typeAndInput = getInput();
        String type = typeAndInput[0];
        String input = typeAndInput[1];

        handleInput(type, input);
    }
}