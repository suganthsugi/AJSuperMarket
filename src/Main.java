import java.util.*;

public class Main {
    public static String[] getConsoleInput() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] typeAndInput = input.split("=>");
        return typeAndInput;
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

    // public static String getOfferName(String input) {
    //     String offerName = input.strip().split("\\|")[0];
    //     return offerName;
    // }

    // public static List<Integer> parseIdForOffer(String input) {
    //     List<String> stringIds = Arrays.asList(input.strip().split(","));
    //     List<Integer> ids = new ArrayList<>();

    //     for (String x : stringIds) {
    //         ids.add(Integer.parseInt(x.strip()));
    //     }
    //     return ids;
    // }

    // public static void applyBuyXMoreForProduct(int id, BuyXMore offer) {
    //     Product currProd = idProdMap.get(id);
    //     offer.applyForProduct(currProd);
    // }

    // public static void applyBuyXMoreForProducts(String ids, BuyXMore offer) {
    //     List<Integer> allIds = parseIdForOffer(ids);
    //     for (Integer id : allIds) {
    //         applyBuyXMoreForProduct(id, offer);
    //     }
    // }

    // public static BuyXMore createBuyXMoreOfferFromInput(String input) {
    //     String[] offerData = input.split("\\|");
    //     int id = Integer.parseInt(offerData[0].strip());
    //     int min_quantity = Integer.parseInt(offerData[1].strip());
    //     int discount_percent = Integer.parseInt(offerData[2].strip());

    //     BuyXMore newOffer = new BuyXMore(id, min_quantity, discount_percent);
    //     offers.put(id, newOffer);

    //     return newOffer;
    // }

    // public static void handleBuyXMore() {

    // }

    // public static void handleOffer(String input){
    //     String offerName = getOfferName(input);
    //     if(offerName.equalsIgnoreCase("BuyXMore")) {
    //         handleBuyXMore();
    //     }
    // }

    public static void handleInput(String type, String input, Store store) {
        if(type.equalsIgnoreCase("INVENTORY")) {
            handleInventory(input, store);
        }
        else if(type.equalsIgnoreCase("SALE")) {
            handleSale(input, store);
        }
        else if(type.equalsIgnoreCase("STOCK")) {
            handleCheckStock(input, store);
        }
//        else if(type.equalsIgnoreCase("OFFER")) {
////            handleOffer(input);
//        }
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